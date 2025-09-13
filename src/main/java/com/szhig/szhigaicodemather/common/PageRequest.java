package com.szhig.szhigaicodemather.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.common
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  20:19
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class PageRequest implements Serializable {

    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}
