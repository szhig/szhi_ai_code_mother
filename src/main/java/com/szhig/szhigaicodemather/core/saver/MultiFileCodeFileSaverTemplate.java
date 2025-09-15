package com.szhig.szhigaicodemather.core.saver;

import cn.hutool.core.util.StrUtil;
import com.szhig.szhigaicodemather.ai.model.MultiFileCodeResult;
import com.szhig.szhigaicodemather.exception.BusinessException;
import com.szhig.szhigaicodemather.exception.ErrorCode;
import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.core.saver
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  13:28
 * @Description: TODO
 * @Version: 1.0
 */
public class MultiFileCodeFileSaverTemplate extends CodeFileSaverTemplate<MultiFileCodeResult> {
    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.MULTI_FILE;
    }

    @Override
    protected void saveFiles(MultiFileCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
        // 保存 CSS 文件
        writeToFile(baseDirPath, "style.css", result.getCssCode());
        // 保存 JS 文件
        writeToFile(baseDirPath, "script.js", result.getJsCode());
    }

    @Override
    protected void validateInput(MultiFileCodeResult result) {
        super.validateInput(result);
        // 至少要有HTML代码， CSS和JS可以为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
        }
    }
}
