package com.szhig.szhigaicodemather.core;

import com.szhig.szhigaicodemather.ai.AiCodeGeneratorService;
import com.szhig.szhigaicodemather.ai.model.HtmlCodeResult;
import com.szhig.szhigaicodemather.ai.model.MultiFileCodeResult;
import com.szhig.szhigaicodemather.core.parse.CodeParserExecutor;
import com.szhig.szhigaicodemather.core.saver.CodeFileSaverExecutor;
import com.szhig.szhigaicodemather.exception.BusinessException;
import com.szhig.szhigaicodemather.exception.ErrorCode;
import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.core
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  10:05
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class AiCodeGeneratorFacade {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;


    private Flux<String> processCodeStream(Flux<String> codeStream, CodeGenTypeEnum codeGenTypeEnum) {
        StringBuilder codeBuilder = new StringBuilder();
        return codeStream.doOnNext(chunk -> {
            codeBuilder.append(chunk);
        }).doOnComplete(() -> {
            try {
                String completeCode = codeBuilder.toString();
                // 使用执行器解析代码
                Object parseResult = CodeParserExecutor.executeParser(completeCode, codeGenTypeEnum);
                // 使用执行器保存代码
                File saveDir = CodeFileSaverExecutor.executorSaver(parseResult, codeGenTypeEnum);
                log.info("保存成功，路径为：" + saveDir.getAbsolutePath());
            } catch (Exception e) {
                log.error("保存失败： {}", e.getMessage());
            }
        });
    }

    /**
     * 统一入口：根据类型生成并保存代码
     *
     * @param userMessage 用户提示词
     * @param codeGenTypeEnum 生成类型
     * @return 保存的目录
     */
    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(userMessage);
                yield CodeFileSaverExecutor.executorSaver(result, CodeGenTypeEnum.HTML);
            }
            case MULTI_FILE -> {
                MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode(userMessage);
                yield CodeFileSaverExecutor.executorSaver(result, CodeGenTypeEnum.MULTI_FILE);
            }
            default -> {
                String errorMessage = "不支持的生成类型：" + codeGenTypeEnum;
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }

    /**
     * 统一入口：根据类型生成并保存代码(流式)
     *
     * @param userMessage 用户提示词
     * @param codeGenTypeEnum 生成类型
     * @return 响应流
     */
    public Flux<String> generateAndSaveCodeStream(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                Flux<String> result = aiCodeGeneratorService.generateHtmlCodeStream(userMessage);
                yield processCodeStream(result, CodeGenTypeEnum.HTML);
            }
            case MULTI_FILE -> {
                Flux<String> result = aiCodeGeneratorService.generateMultiFileCodeStream(userMessage);
                yield processCodeStream(result, CodeGenTypeEnum.MULTI_FILE);
            }
            default -> {
                String errorMessage = "不支持的生成类型：" + codeGenTypeEnum;
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }

    /**
     * 生成HTML模式的代码并保存
     *
     * @param userMessage 用户提示词
     * @return 保存的目录
     */
    private File generateAndSaveHtmlCode(String userMessage) {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(userMessage);
        return CodeFileSaver.saveHtmlCodeResult(result);
    }

    /**
     * 生成多文件模式的代码并保存
     *
     * @param userMessage 用户提示词
     * @return 保存的目录
     */
    private File generateAndSaveMultiFileCode(String userMessage) {
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode(userMessage);
        return CodeFileSaver.saveMultiFileCodeResult(result);
    }

    /**
     * 生成HTML模式的代码并保存(流式)
     *
     * @param userMessage 用户提示词
     * @return 响应流
     */
    private Flux<String> generateAndSaveHtmlCodeStream(String userMessage) {
        Flux<String> result = aiCodeGeneratorService.generateHtmlCodeStream(userMessage);
        // 当流式返回生成代码完成后，再保存代码
        StringBuilder codeBuilder = new StringBuilder();
        return result
                .doOnNext(chunk -> {
                    codeBuilder.append(chunk);
                })
                .doOnComplete(() -> {
                    try {
                        String completeHtmlCode = codeBuilder.toString();
                        HtmlCodeResult htmlCodeResult = CodeParser.parseHtmlCode(completeHtmlCode);
                        // 保存代码到文件
                        File saveDir = CodeFileSaver.saveHtmlCodeResult(htmlCodeResult);
                        log.info("保存成功，路径为：" + saveDir.getAbsolutePath());
                    } catch (Exception e) {
                        log.error("保存失败： {}", e.getMessage());
                    }
                });
    }

    /**
     * 生成多文件模式的代码并保存(流式)
     *
     * @param userMessage 用户提示词
     * @return 响应流
     */
    private Flux<String> generateAndSaveMultiFileCodeStream(String userMessage) {
        Flux<String> result = aiCodeGeneratorService.generateMultiFileCodeStream(userMessage);
        // 当流式返回生成代码完成后，再保存代码
        StringBuilder codeBuilder = new StringBuilder();
        return result
                .doOnNext(chunk -> {
                    codeBuilder.append(chunk);
                })
                .doOnComplete(() -> {
                    try {
                        String completeMultiFileCode = codeBuilder.toString();
                        MultiFileCodeResult multiFileCode = CodeParser.parseMultiFileCode(completeMultiFileCode);
                        // 保存代码到文件
                        File saveDir = CodeFileSaver.saveMultiFileCodeResult(multiFileCode);
                        log.info("保存成功，路径为：" + saveDir.getAbsolutePath());
                    } catch (Exception e) {
                        log.error("保存失败： {}", e.getMessage());
                    }
                });
    }
}
