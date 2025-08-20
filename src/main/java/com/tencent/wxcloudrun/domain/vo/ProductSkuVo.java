package com.tencent.wxcloudrun.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:26
 * @Since: 1.0
 */
@Data
public class ProductSkuVo {
    private Integer productId;

    private String productName;

    private List<SkuInfoVo> skus;
}
