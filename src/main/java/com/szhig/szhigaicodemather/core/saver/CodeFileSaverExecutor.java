package com.szhig.szhigaicodemather.core.saver;

import com.szhig.szhigaicodemather.ai.model.HtmlCodeResult;
import com.szhig.szhigaicodemather.ai.model.MultiFileCodeResult;
import com.szhig.szhigaicodemather.exception.BusinessException;
import com.szhig.szhigaicodemather.exception.ErrorCode;
import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;

import java.io.File;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.core.saver
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  13:31
 * @Description: TODO
 * @Version: 1.0
 */
public class CodeFileSaverExecutor {
    private static final HtmlCodeFileSaverTemplate htmlCodeFileSaverTemplate = new HtmlCodeFileSaverTemplate();
    private static final MultiFileCodeFileSaverTemplate multiFileCodeFileSaverTemplate = new MultiFileCodeFileSaverTemplate();

    public static File executorSaver(Object codeResult, CodeGenTypeEnum codeGenTypeEnum) {
        return switch (codeGenTypeEnum) {
            case HTML -> htmlCodeFileSaverTemplate.saveCode((HtmlCodeResult) codeResult);
            case MULTI_FILE -> multiFileCodeFileSaverTemplate.saveCode((MultiFileCodeResult) codeResult);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR, "不支持的代码生成类型：" + codeGenTypeEnum.getValue());
        };
    }
}
