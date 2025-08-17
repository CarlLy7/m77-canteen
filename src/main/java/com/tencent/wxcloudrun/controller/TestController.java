package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-17 15:15
 * @Since: 1.0
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @GetMapping("hello")
    public ApiResponse hello(@RequestHeader Map<String, String> header){
        System.out.println(header);
        return ApiResponse.ok("hello world");
    }
}
