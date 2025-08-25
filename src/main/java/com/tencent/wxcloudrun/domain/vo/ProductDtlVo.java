package com.tencent.wxcloudrun.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:09
 * @Since: 1.0
 */
@Data
public class ProductDtlVo {

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
     * 分类id
     */
    private Integer categoryId;
}
