package com.szhig.szhigaicodemather.common;

import com.szhig.szhigaicodemather.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.common
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  20:00
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(int code, T data) {
        this(code, data,"");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
