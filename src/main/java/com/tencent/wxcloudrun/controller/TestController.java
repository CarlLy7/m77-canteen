package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-17 15:15
 * @Since: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("hello")
    public ApiResponse hello(){
        return ApiResponse.ok("hello world");
    }
}
