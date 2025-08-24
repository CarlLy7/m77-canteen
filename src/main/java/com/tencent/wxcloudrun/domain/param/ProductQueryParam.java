package com.tencent.wxcloudrun.domain.param;

import lombok.Data;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.20
 * @Since: 1.0
 */
@Data
public class ProductQueryParam {
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 主厨id
     */
    private Integer canteenId;
}
