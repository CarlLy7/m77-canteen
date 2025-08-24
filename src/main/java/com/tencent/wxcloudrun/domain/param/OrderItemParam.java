package com.tencent.wxcloudrun.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-20 21:08
 * @Since: 1.0
 */
@Data
public class OrderItemParam {
    @NotNull(message = "商品id不能为空")
    private Integer productId;

    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @NotNull(message = "数量不能为空")
    private Integer countNum;

    @NotNull(message = "商品sku不能为空")
    private Integer skuId;

    @NotBlank(message = "商品sku名称不能为空")
    private String skuName;

    private String imageUrl;

    @NotNull(message = "商品价格不能为空")
    private BigDecimal price;

    /**
     * 主厨id
     */
    @NotNull(message = "主厨id不能为空")
    private Integer canteenId;


}
