package com.szhig.szhigaicodemather.exception;

import com.szhig.szhigaicodemather.common.BaseResponse;
import com.szhig.szhigaicodemather.common.ResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.exception
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  20:03
 * @Description: TODO 全局异常处理器
 * @Version: 1.0
 */
@RestControllerAdvice
@Hidden
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(exception = BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(exception = RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
