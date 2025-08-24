package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.domain.param.SubScribeParam;
import com.tencent.wxcloudrun.model.BbUserSubscribe;
import com.tencent.wxcloudrun.service.base.BbUserSubscribeService;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @param param
     * @return
     */
    @PostMapping("/save")
    public ApiResponse save(HttpServletRequest request, @RequestBody @Validated SubScribeParam param){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        List<String> templateIds = param.getTemplateIds();
        //已经订阅的
        List<BbUserSubscribe> subscribeList = bbUserSubscribeService.list(Wrappers.lambdaQuery(BbUserSubscribe.class)
                .eq(BbUserSubscribe::getOpenId, openId)
                .in(BbUserSubscribe::getTemplateId, templateIds));
        for (String templateId : templateIds) {
            if (subscribeList.contains(templateId)){
                continue;
            }
            BbUserSubscribe bbUserSubscribe = new BbUserSubscribe();
            bbUserSubscribe.setOpenId(openId);
            bbUserSubscribe.setTemplateId(templateId);
            bbUserSubscribeService.save(bbUserSubscribe);
        }
        return ApiResponse.ok();
    }


    //TODO 查询用户订阅消息

}
