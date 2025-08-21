package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.domain.param.CreateScoreParam;
import com.tencent.wxcloudrun.domain.param.OrderCreateParam;
import com.tencent.wxcloudrun.domain.vo.OrderVo;
import com.tencent.wxcloudrun.service.OrderService;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 订单接口
 * @author: carl
 * @createDate: 2025-08-18 21:23
 * @Since: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 下单
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/createOrder")
    public ApiResponse createOrder(HttpServletRequest request, @RequestBody @Validated OrderCreateParam param){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        orderService.createOrder(openId,param);
        return ApiResponse.ok();
    }


    /**
     * 查询订单列表
     * @param request
     * @return
     */
    @GetMapping("/getOrder")
    public ApiResponse getOrder(HttpServletRequest request){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        List<OrderVo> list=orderService.getOrder(openId);
        return ApiResponse.ok(list);
    }


    @PostMapping("/createScore")
    public ApiResponse createScore(HttpServletRequest request, @RequestBody @Validated  CreateScoreParam param){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        orderService.createScore(openId,param);
        return ApiResponse.ok();
    }


}
