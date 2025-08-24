package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName bb_canteen_info
 */
@TableName(value ="bb_canteen_info")
@Data
public class BbCanteenInfo implements Serializable {
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
     * 主厨名称
     */
    private String canteenName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}