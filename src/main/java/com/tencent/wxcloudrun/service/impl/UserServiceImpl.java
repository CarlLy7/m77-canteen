package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tencent.wxcloudrun.domain.param.UserInfoSaveOrUpdateParam;
import com.tencent.wxcloudrun.domain.vo.UserInfoVo;
import com.tencent.wxcloudrun.model.BbUser;
import com.tencent.wxcloudrun.service.UserService;
import com.tencent.wxcloudrun.service.base.BbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 20:45
 * @Since: 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private BbUserService bbUserService;

    @Override
    public UserInfoVo getUserInfoByOpenId(String openId) {
        BbUser bbUser = bbUserService.getOne(Wrappers.lambdaQuery(BbUser.class)
                .eq(BbUser::getOpenId, openId));
        if (Objects.isNull(bbUser)) {
            return null;
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtil.copyProperties(bbUser, userInfoVo);
        return userInfoVo;
    }

    @Override
    public UserInfoVo saveOrUpdate(String openId, UserInfoSaveOrUpdateParam param) {
        BbUser bbUser = bbUserService.getOne(Wrappers.lambdaQuery(BbUser.class)
                .eq(BbUser::getOpenId, openId));
        //新增
        if (Objects.isNull(bbUser)) {
            BbUser saveUserInfo = new BbUser();
            saveUserInfo.setOpenId(openId);
            saveUserInfo.setNickName(param.getNickName());
            saveUserInfo.setAvatarUrl(param.getAvatarUrl());
            bbUserService.save(saveUserInfo);
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setId(saveUserInfo.getId());
            userInfoVo.setOpenId(saveUserInfo.getOpenId());
            userInfoVo.setNickName(saveUserInfo.getNickName());
            userInfoVo.setAvatarUrl(saveUserInfo.getAvatarUrl());
            return userInfoVo;
        }
        //修改
        bbUserService.update(Wrappers.lambdaUpdate(BbUser.class)
                .eq(BbUser::getId, bbUser.getId())
                .eq(BbUser::getOpenId, openId)
                .set(StrUtil.isNotBlank(param.getAvatarUrl()), BbUser::getAvatarUrl, param.getAvatarUrl())
                .set(StrUtil.isNotBlank(param.getNickName()), BbUser::getNickName, param.getNickName()));
        BbUser user = bbUserService.getById(bbUser.getId());
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setId(bbUser.getId());
        userInfoVo.setOpenId(openId);
        userInfoVo.setNickName(user.getNickName());
        userInfoVo.setAvatarUrl(user.getAvatarUrl());
        return userInfoVo;
    }
}
