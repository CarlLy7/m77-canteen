package com.tencent.wxcloudrun.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-20 21:19
 * @Since: 1.0
 */
@Data
public class OrderDtlVo {
    private Integer productId;

    private String productName;

    private Integer skuId;

    private String skuName;

    private BigDecimal price;

    private String imageUrl;

    private Integer count;
}
