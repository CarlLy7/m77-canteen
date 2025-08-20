package com.tencent.wxcloudrun.domain.vo;

import lombok.Data;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 20:50
 * @Since: 1.0
 */
@Data
public class UserInfoVo {
    private String id;


    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 文件id
     */
    private Integer fileId;
}
