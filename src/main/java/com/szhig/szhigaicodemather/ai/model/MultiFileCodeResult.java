package com.szhig.szhigaicodemather.ai.model;

import jdk.jfr.Description;
import lombok.Data;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.ai.model
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  09:20
 * @Description: TODO
 * @Version: 1.0
 */
@Description("生成多个代码文件的结果")
@Data
public class MultiFileCodeResult {

    @Description("HTML代码")
    private String htmlCode;

    @Description("CSS代码")
    private String cssCode;

    @Description("JS代码")
    private String jsCode;

    @Description("生成代码的描述")
    private String description;
}
