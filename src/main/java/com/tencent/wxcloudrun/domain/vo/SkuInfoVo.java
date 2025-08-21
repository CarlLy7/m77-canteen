package com.tencent.wxcloudrun.domain.vo;

import lombok.Data;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:27
 * @Since: 1.0
 */
@Data
public class SkuInfoVo {
    private Integer id;

    /**
     * sku名称
     */
    private String name;

    /**
     * sku价格
     */
    private String price;

    /**
     * sku图片链接
     */
    private String imageUrl;

    /**
     * 商品id
     */
    private Integer productId;
}
