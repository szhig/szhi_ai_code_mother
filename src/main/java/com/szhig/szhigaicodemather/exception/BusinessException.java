package com.szhig.szhigaicodemather.exception;

import lombok.Getter;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.exception
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  19:12
 * @Description: TODO
 * @Version: 1.0
 */
@Getter
public class BusinessException extends RuntimeException{
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}
