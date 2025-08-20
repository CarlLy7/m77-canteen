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
 * @TableName bb_order_dtl
 */
@TableName(value ="bb_order_dtl")
@Data
public class BbOrderDtl implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品skuId
     */
    private Integer skuId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 规格名称
     */
    private String skuName;

    private BigDecimal price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}