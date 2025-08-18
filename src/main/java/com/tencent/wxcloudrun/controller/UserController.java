package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.domain.param.UserInfoSaveOrUpdateParam;
import com.tencent.wxcloudrun.domain.vo.UserInfoVo;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户接口
 * @author: carl
 * @createDate: 2025-08-18 20:43
 * @Since: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 通过openId查询用户信息
     * @param request
     * @return
     */
    @GetMapping("/getUserInfoByOpenId")
    public ApiResponse getUserInfoByOpenId(HttpServletRequest request){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        UserInfoVo userInfoVo=userService.getUserInfoByOpenId(openId);
        return ApiResponse.ok(userInfoVo);
    }

    /**
     * 新增或者修改用户信息
     * @param request
     * @param param
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public ApiResponse saveOrUpdate(HttpServletRequest request,
                                    @RequestBody UserInfoSaveOrUpdateParam param){
        String openId = request.getHeader("X-WX-OPENID");
        Assert.hasText(openId,"请求头中不包含openId");
        UserInfoVo userInfoVo=userService.saveOrUpdate(openId,param);
        return ApiResponse.ok(userInfoVo);
    }


}
