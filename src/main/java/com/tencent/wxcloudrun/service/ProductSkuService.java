package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.domain.vo.ProductSkuVo;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:24
 * @Since: 1.0
 */
public interface ProductSkuService {
    ProductSkuVo getSkuInfoByProductId(Integer productId);
}
