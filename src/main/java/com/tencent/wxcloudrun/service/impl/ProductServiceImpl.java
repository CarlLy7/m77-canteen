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
import java.util.Objects;
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
        //查询商品分类
        List<BbCategory> categoryList = bbCategoryService.list(Wrappers.lambdaQuery(BbCategory.class)
                .eq(Objects.nonNull(param.getCanteenId()),BbCategory::getCanteenId,param.getCanteenId())
                .orderByAsc(BbCategory::getSort));
        if (CollUtil.isEmpty(categoryList)){
            return new ArrayList<>();
        }
        List<ProductVo> result = BeanUtil.copyToList(categoryList, ProductVo.class);
        //查询商品
        List<BbProduct> productList = bbProductService.list(Wrappers.lambdaQuery(BbProduct.class)
                .like(StrUtil.isNotBlank(param.getProductName()),BbProduct::getProductName,param.getProductName())
                .eq(Objects.nonNull(param.getCanteenId()),BbProduct::getCanteenId,param.getCanteenId())
                .orderByAsc(BbProduct::getSort));
        if (CollUtil.isEmpty(productList)){
            return result;
        }

        Map<Integer, ProductVo> productVoMap = result.stream()
                .collect(Collectors.toMap(ProductVo::getCategoryId, obj -> obj));

        Map<Integer, List<BbProduct>> productGroup = productList.stream()
                .collect(Collectors.groupingBy(BbProduct::getCategoryId));

        for (Map.Entry<Integer, ProductVo> entry : productVoMap.entrySet()) {
            Integer categoryId = entry.getKey();
            if (!productGroup.containsKey(categoryId)){
                continue;
            }
            entry.getValue().setDtls(BeanUtil.copyToList(productGroup.get(categoryId),ProductDtlVo.class));
        }

        return result;
    }
}
