package com.szhig.szhigaicodemather.controller;

import com.szhig.szhigaicodemather.common.BaseResponse;
import com.szhig.szhigaicodemather.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: szhig-ai-code-mather
 * @BelongsPackage: com.szhig.szhigaicodemather.controller
 * @Author: shizhiguang
 * @CreateTime: 2025-09-13  18:53
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public BaseResponse<String> test() {
        return ResultUtils.success("ok");
    }
}
