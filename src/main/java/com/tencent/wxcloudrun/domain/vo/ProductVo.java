package com.tencent.wxcloudrun.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:09
 * @Since: 1.0
 */
@Data
public class ProductVo {
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类下的商品列表
     */
    private List<ProductDtlVo> dtls;
}
