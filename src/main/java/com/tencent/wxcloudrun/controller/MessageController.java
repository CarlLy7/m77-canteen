package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.22
 * @Since: 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @PostMapping("/push")
    public ApiResponse push(String text) {
        System.out.println(text);
        return ApiResponse.ok();
    }
}
