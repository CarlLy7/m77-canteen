package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.BbUserSubscribe;
import com.tencent.wxcloudrun.service.base.BbUserSubscribeService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.22
 * @Since: 1.0
 */
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {
    @Resource
    private BbUserSubscribeService bbUserSubscribeService;

    /**
     * 用户订阅信息保存
     * @param request
     * @param templateId
     * @return
     */
    @GetMapping("save")
    public ApiResponse save(HttpServletRequest request,@RequestParam("templateId") String templateId){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        long count = bbUserSubscribeService.count(Wrappers.lambdaQuery(BbUserSubscribe.class)
                .eq(BbUserSubscribe::getOpenId, openId)
                .eq(BbUserSubscribe::getTemplateId, templateId));
        if (count>0){
            return ApiResponse.ok();
        }
        BbUserSubscribe bbUserSubscribe = new BbUserSubscribe();
        bbUserSubscribe.setOpenId(openId);
        bbUserSubscribe.setTemplateId(templateId);
        bbUserSubscribeService.save(bbUserSubscribe);
        return ApiResponse.ok();
    }


    //TODO 查询用户订阅消息

}
