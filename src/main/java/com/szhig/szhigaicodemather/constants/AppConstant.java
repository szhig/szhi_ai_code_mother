package com.szhig.szhigaicodemather.constants;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.constants
 * @Author: shizhiguang
 * @CreateTime: 2025-09-16  09:08
 * @Description: TODO
 * @Version: 1.0
 */
public interface AppConstant {

    /**
     * 精选应用的优先级
     */
    Integer GOOD_APP_PRIORITY = 99;

    /**
     * 默认应用的优先级
     */
    Integer DEFAULT_APP_PRIORITY = 0;

    /**
    * 应用生成目录
     */
    String CODE_OUTPUT_ROOT_DIR = System.getProperty("user.dir") + "/tmp/code_output";
}
