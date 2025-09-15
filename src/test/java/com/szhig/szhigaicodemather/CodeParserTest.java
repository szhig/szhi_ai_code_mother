package com.szhig.szhigaicodemather;

import com.szhig.szhigaicodemather.ai.model.HtmlCodeResult;
import com.szhig.szhigaicodemather.ai.model.MultiFileCodeResult;
import com.szhig.szhigaicodemather.core.AiCodeGeneratorFacade;
import com.szhig.szhigaicodemather.core.CodeParser;
import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  10:52
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class CodeParserTest {

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void parseHtmlCode() {
        String codeContent = """
                随便写一段描述：
                ```html
                <!DOCTYPE html>
                <html>
                <head>
                    <title>测试页面</title>
                </head>
                <body>
                    <h1>Hello World!</h1>
                </body>
                </html>
                ```
                随便写一段描述
                """;
        HtmlCodeResult result = CodeParser.parseHtmlCode(codeContent);
        assertNotNull(result);
        assertNotNull(result.getHtmlCode());
    }

    @Test
    void parseMultiFileCode() {
        String codeContent = """
                创建一个完整的网页：
                ```html
                <!DOCTYPE html>
                <html>
                <head>
                    <title>多文件示例</title>
                    <link rel="stylesheet" href="style.css">
                </head>
                <body>
                    <h1>欢迎使用</h1>
                    <script src="script.js"></script>
                </body>
                </html>
                ```
                ```css
                h1 {
                    color: blue;
                    text-align: center;
                }
                ```
                ```js
                console.log('页面加载完成');
                ```
                文件创建完成！
                """;
        MultiFileCodeResult result = CodeParser.parseMultiFileCode(codeContent);
        assertNotNull(result);
        assertNotNull(result.getHtmlCode());
        assertNotNull(result.getCssCode());
        assertNotNull(result.getJsCode());
    }

    @Test
    void generateAndSaveCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("任务记录网站", CodeGenTypeEnum.MULTI_FILE);
        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();
        // 验证结果
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
    }

}

