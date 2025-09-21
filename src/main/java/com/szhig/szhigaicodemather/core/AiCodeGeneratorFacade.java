package com.szhig.szhigaicodemather.core;

import cn.hutool.json.JSONUtil;
import com.szhig.szhigaicodemather.ai.AiCodeGeneratorService;
import com.szhig.szhigaicodemather.ai.AiCodeGeneratorServiceFactory;
import com.szhig.szhigaicodemather.ai.model.HtmlCodeResult;
import com.szhig.szhigaicodemather.ai.model.MultiFileCodeResult;
import com.szhig.szhigaicodemather.ai.model.message.AiResponseMessage;
import com.szhig.szhigaicodemather.ai.model.message.ToolExecutedMessage;
import com.szhig.szhigaicodemather.ai.model.message.ToolRequestMessage;
import com.szhig.szhigaicodemather.core.parse.CodeParserExecutor;
import com.szhig.szhigaicodemather.core.saver.CodeFileSaverExecutor;
import com.szhig.szhigaicodemather.exception.BusinessException;
import com.szhig.szhigaicodemather.exception.ErrorCode;
import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.TokenStream;
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
    private AiCodeGeneratorServiceFactory aiCodeGeneratorServiceFactory;

    private Flux<String> processCodeStream(Flux<String> codeStream, CodeGenTypeEnum codeGenTypeEnum, Long appId) {
        StringBuilder codeBuilder = new StringBuilder();
        return codeStream.doOnNext(chunk -> {
            codeBuilder.append(chunk);
        }).doOnComplete(() -> {
            try {
                String completeCode = codeBuilder.toString();
                // 使用执行器解析代码
                Object parseResult = CodeParserExecutor.executeParser(completeCode, codeGenTypeEnum);
                // 使用执行器保存代码
                File saveDir = CodeFileSaverExecutor.executorSaver(parseResult, codeGenTypeEnum, appId);
                log.info("保存成功，路径为：" + saveDir.getAbsolutePath());
            } catch (Exception e) {
                log.error("保存失败： {}", e.getMessage());
            }
        });
    }

    private Flux<String> processTokenStream(TokenStream tokenStream) {
        return Flux.create(sink -> {
            tokenStream.onPartialResponse((String parialResponse) -> {
                AiResponseMessage aiResponseMessage = new AiResponseMessage(parialResponse);
                sink.next(JSONUtil.toJsonStr(aiResponseMessage));
            })
            .onPartialToolExecutionRequest((index, toolExecutionRequest) -> {
                ToolRequestMessage toolRequestMessage = new ToolRequestMessage(toolExecutionRequest);
                sink.next(JSONUtil.toJsonStr(toolRequestMessage));
            })
            .onToolExecuted((toolExecution) -> {
                ToolExecutedMessage toolExecutedMessage = new ToolExecutedMessage(toolExecution);
                sink.next(JSONUtil.toJsonStr(toolExecutedMessage));
            })
            .onCompleteResponse((ChatResponse response) -> {
                sink.complete();
            })
            .onError((Throwable error) -> {
                error.printStackTrace();
                sink.error(error);
            })
            .start();
        });
    }

    /**
     * 统一入口：根据类型生成并保存代码
     *
     * @param userMessage 用户提示词
     * @param codeGenTypeEnum 生成类型
     * @return 保存的目录
     */
    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenTypeEnum, Long appId) {
        AiCodeGeneratorService aiCodeGeneratorService = aiCodeGeneratorServiceFactory.getAiCodeGeneratorService(appId, codeGenTypeEnum);
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(userMessage);
                yield CodeFileSaverExecutor.executorSaver(result, CodeGenTypeEnum.HTML, appId);
            }
            case MULTI_FILE -> {
                MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode(userMessage);
                yield CodeFileSaverExecutor.executorSaver(result, CodeGenTypeEnum.MULTI_FILE, appId);
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
    public Flux<String> generateAndSaveCodeStream(String userMessage, CodeGenTypeEnum codeGenTypeEnum, Long appId) {
        AiCodeGeneratorService aiCodeGeneratorService = aiCodeGeneratorServiceFactory.getAiCodeGeneratorService(appId, codeGenTypeEnum);
        if (codeGenTypeEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> {
                Flux<String> result = aiCodeGeneratorService.generateHtmlCodeStream(userMessage);
                yield processCodeStream(result, CodeGenTypeEnum.HTML, appId);
            }
            case MULTI_FILE -> {
                Flux<String> result = aiCodeGeneratorService.generateMultiFileCodeStream(userMessage);
                yield processCodeStream(result, CodeGenTypeEnum.MULTI_FILE, appId);
            }
            case VUE_PROJECT -> {
                TokenStream tokenStream = aiCodeGeneratorService.generateVueProjectCodeStream(appId, userMessage);
                yield processTokenStream(tokenStream);
            }
            default -> {
                String errorMessage = "不支持的生成类型：" + codeGenTypeEnum;
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }


}
