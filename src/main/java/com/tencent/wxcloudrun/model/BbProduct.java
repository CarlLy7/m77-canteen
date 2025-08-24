package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName bb_product
 */
@TableName(value ="bb_product")
@Data
public class BbProduct implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品图片
     */
    private String imageUrl;

    /**
     * 商品分类id
     */
    private Integer categoryId;

    private Integer sort;

    /**
     * 主厨id
     */
    private Integer canteenId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}