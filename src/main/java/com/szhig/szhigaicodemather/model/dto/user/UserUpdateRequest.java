package com.szhig.szhigaicodemather.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.model.dto.user
 * @Author: shizhiguang
 * @CreateTime: 2025-09-14  09:41
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class UserUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}
