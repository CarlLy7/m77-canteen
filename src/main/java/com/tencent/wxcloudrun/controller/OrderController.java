package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 21:23
 * @Since: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;


}
