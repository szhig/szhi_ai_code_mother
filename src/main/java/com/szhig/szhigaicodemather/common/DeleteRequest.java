package com.szhig.szhigaicodemather.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.common
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  20:20
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class DeleteRequest implements Serializable {

    private Long id;

    private static final long serialVersionUID = 1L;
}
