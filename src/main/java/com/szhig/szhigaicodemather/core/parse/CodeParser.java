package com.szhig.szhigaicodemather.core.parse;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.core.parse
 * @Author: shizhiguang
 * @CreateTime: 2025-09-15  13:11
 * @Description: TODO
 * @Version: 1.0
 */
public interface CodeParser<T> {
    /**
     * 解析代码内容
     *
     * @param codeContent 原始代码内容
     * @return 解析后的结果对象
     */
    T parseCode(String codeContent);
}
