package com.szhig.szhigaicodemather.core.saver;

import cn.hutool.core.util.StrUtil;
import com.szhig.szhigaicodemather.ai.model.HtmlCodeResult;
import com.szhig.szhigaicodemather.exception.BusinessException;
import com.szhig.szhigaicodemather.exception.ErrorCode;
import com.szhig.szhigaicodemather.model.enums.CodeGenTypeEnum;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.core.saver
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  13:26
 * @Description: TODO
 * @Version: 1.0
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {
    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        // 保存HTML文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML代码不能为空");
        }
    }
}
