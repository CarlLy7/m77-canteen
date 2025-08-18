package com.tencent.wxcloudrun.domain.param;

import lombok.Data;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 21:14
 * @Since: 1.0
 */
@Data
public class UserInfoSaveOrUpdateParam {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像url
     */
    private String avatarUrl;
}
