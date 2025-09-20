package com.szhig.szhigaicodemather.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.model.dto.app
 * @Author: shizhiguang
 * @CreateTime: 2025-09-16  08:16
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class AppAddRequest implements Serializable {

    /**
     * 应用初始化的prompt
     */
    private String initPrompt;

    private static final long serialVersionUID = 1L;
}
