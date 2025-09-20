package com.szhig.szhigaicodemather.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.model.dto.app
 * @Author: shizhiguang
 * @CreateTime: 2025-09-16  08:26
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class AppUpdateRequest implements Serializable {

    /*
     * id
     **/
    private Long id;

    /*
     * 应用名称
     **/
    private String appName;

    private static final long serialVersionUID = 1L;
}
