package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.domain.param.UserInfoSaveOrUpdateParam;
import com.tencent.wxcloudrun.domain.vo.UserInfoVo;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 20:44
 * @Since: 1.0
 */
public interface UserService {
    UserInfoVo getUserInfoByOpenId(String openId);

    UserInfoVo saveOrUpdate(String openId, UserInfoSaveOrUpdateParam param);
}
