package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.domain.vo.ProductSkuVo;
import com.tencent.wxcloudrun.service.ProductSkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:24
 * @Since: 1.0
 */
@RestController
@RequestMapping("/sku")
public class ProductSkuController {
    @Resource
    private ProductSkuService productSkuService;

    /**
     * 通过商品id获取对应的sku列表,,,,,
     * @param productId
     * @return
     */
    @GetMapping("/getSkuInfoByProductId")
    public ApiResponse getSkuInfoByProductId(@RequestParam(value="productId") Integer productId){
        ProductSkuVo productSkuVo=productSkuService.getSkuInfoByProductId(productId);
        return ApiResponse.ok(productSkuVo);
    }
}
