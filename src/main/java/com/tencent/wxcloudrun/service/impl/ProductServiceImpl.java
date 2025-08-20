package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tencent.wxcloudrun.domain.param.ProductQueryParam;
import com.tencent.wxcloudrun.domain.vo.ProductDtlVo;
import com.tencent.wxcloudrun.domain.vo.ProductVo;
import com.tencent.wxcloudrun.model.BbCategory;
import com.tencent.wxcloudrun.model.BbProduct;
import com.tencent.wxcloudrun.service.ProductService;
import com.tencent.wxcloudrun.service.base.BbCategoryService;
import com.tencent.wxcloudrun.service.base.BbProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:07
 * @Since: 1.0
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Resource
    private BbProductService bbProductService;

    @Resource
    private BbCategoryService bbCategoryService;


    @Override
    public List<ProductVo> getProduct(ProductQueryParam param) {
        List<BbProduct> productList = bbProductService.list(Wrappers.lambdaQuery(BbProduct.class)
                .like(StrUtil.isNotBlank(param.getProductName()),BbProduct::getProductName,param.getProductName())
                .orderByAsc(BbProduct::getSort));
        List<ProductVo> result = new ArrayList<>();
        if (CollUtil.isNotEmpty(productList)) {
            List<ProductDtlVo> productDtlVos = BeanUtil.copyToList(productList, ProductDtlVo.class);
            Map<Integer, List<ProductDtlVo>> productDtlVoGroup = productDtlVos.stream()
                    .collect(Collectors.groupingBy(ProductDtlVo::getCategoryId));
            List<BbCategory> categoryList = bbCategoryService.list(Wrappers.lambdaQuery(BbCategory.class)
                    .orderByAsc(BbCategory::getSort));
            if (CollUtil.isNotEmpty(categoryList)) {
                Map<Integer, BbCategory> categoryMap = categoryList.stream()
                        .collect(Collectors.toMap(BbCategory::getCategoryId, obj -> obj));
                for (Map.Entry<Integer, List<ProductDtlVo>> entry : productDtlVoGroup.entrySet()) {
                    ProductVo productVo = new ProductVo();
                    Integer categoryId = entry.getKey();
                    if (!categoryMap.containsKey(categoryId)) {
                        continue;
                    }
                    productVo.setCategoryId(categoryId);
                    productVo.setCategoryName(categoryMap.get(categoryId).getCategoryName());
                    productVo.setDtls(entry.getValue());
                    result.add(productVo);
                }
            }
        }
        return result;
    }
}
