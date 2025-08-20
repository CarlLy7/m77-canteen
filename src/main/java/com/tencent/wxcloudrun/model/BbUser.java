package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName bb_user
 */
@TableName(value ="bb_user")
@Data
public class BbUser implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String openId;

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
    private String fileId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}