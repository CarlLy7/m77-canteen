package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName bb_category
 */
@TableName(value ="bb_category")
@Data
public class BbCategory implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 
     */
    private String categoryName;

    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}