package com.szhig.szhigaicodemather.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.model.dto.user
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  23:12
 * @Description: TODO
 * @Version: 1.0
 */

@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -3191241716373120793L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
