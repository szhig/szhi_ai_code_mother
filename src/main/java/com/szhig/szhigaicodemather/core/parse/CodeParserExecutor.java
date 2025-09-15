package com.szhig.szhigaicodemather.core.parse;

import com.szhig.szhigaicodemather.exception.BusinessException;
import com.szhig.szhigaicodemather.exception.ErrorCode;
import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.core.parse
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  13:17
 * @Description: TODO
 * @Version: 1.0
 */
public class CodeParserExecutor {
    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();
    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenTypeEnum) {
        switch (codeGenTypeEnum) {
            case HTML:
                return htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE:
                return multiFileCodeParser.parseCode(codeContent);
            default:
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型：" + codeGenTypeEnum);
        }
    }
}
