package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户订阅消息
 * @TableName bb_user_subscribe
 */
@TableName(value ="bb_user_subscribe")
@Data
public class BbUserSubscribe implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String openId;

    /**
     * 
     */
    private String templateId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}