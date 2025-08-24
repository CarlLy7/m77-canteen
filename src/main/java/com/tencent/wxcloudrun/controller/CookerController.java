package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.BbCanteenInfo;
import com.tencent.wxcloudrun.service.base.BbCanteenInfoService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 主厨接口
 * @author: carl
 * @createDate: 2025-08-24 11:13
 * @Since: 1.0
 */
@RestController
@RequestMapping("/cooker")
public class CookerController {
    @Resource
    private BbCanteenInfoService bbCanteenInfoService;

    @GetMapping("/getAll")
    public ApiResponse getAll(HttpServletRequest request){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        List<BbCanteenInfo> list = bbCanteenInfoService.list();
        return ApiResponse.ok(list);
    }
}
