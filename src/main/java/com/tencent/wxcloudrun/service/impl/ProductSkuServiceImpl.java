package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tencent.wxcloudrun.domain.convert.ProductSkuConvert;
import com.tencent.wxcloudrun.domain.vo.ProductSkuVo;
import com.tencent.wxcloudrun.domain.vo.SkuInfoVo;
import com.tencent.wxcloudrun.model.BbProduct;
import com.tencent.wxcloudrun.model.BbProductSku;
import com.tencent.wxcloudrun.service.ProductSkuService;
import com.tencent.wxcloudrun.service.base.BbProductService;
import com.tencent.wxcloudrun.service.base.BbProductSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:24
 * @Since: 1.0
 */
@Service
@Slf4j
public class ProductSkuServiceImpl implements ProductSkuService {
    @Resource
    private BbProductService bbProductService;

    @Resource
    private BbProductSkuService bbProductSkuService;

    @Override
    public ProductSkuVo getSkuInfoByProductId(Integer productId) {
        List<BbProductSku> skuList = bbProductSkuService.list(Wrappers.lambdaQuery(BbProductSku.class)
                .eq(BbProductSku::getProductId, productId));
        if (CollUtil.isEmpty(skuList)) {
            return new ProductSkuVo();
        }
        BbProduct product = bbProductService.getById(productId);
        Assert.notNull(product, "商品不存在");
        List<SkuInfoVo> skuInfoVos = ProductSkuConvert.INSTANCE.bbProductSkusToSkuInfoVos(skuList);
        ProductSkuVo productSkuVo = new ProductSkuVo();
        productSkuVo.setProductId(productId);
        productSkuVo.setProductName(product.getProductName());
        productSkuVo.setSkus(skuInfoVos);
        return productSkuVo;
    }
}
