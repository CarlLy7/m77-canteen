package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/message")
public class MessageController {
    @PostMapping("/push")
    public ApiResponse push(String text) {
        log.info("receive message=[{}]",text);
        return ApiResponse.ok();
    }
}
