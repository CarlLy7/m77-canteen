package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.domain.vo.ProductVo;

import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:07
 * @Since: 1.0
 */
public interface ProductService {
    List<ProductVo> getProduct();
}
