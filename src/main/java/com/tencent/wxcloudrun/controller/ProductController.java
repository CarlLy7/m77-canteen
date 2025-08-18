package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.domain.vo.ProductVo;
import com.tencent.wxcloudrun.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 22:07
 * @Since: 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 查询商品分类以及分类下的商品列表
     * @return
     */
    @GetMapping("/getProduct")
    public ApiResponse getProduct(){
        //TODO 后续可能要根据商家进行区分
        List<ProductVo> list=productService.getProduct();
        return ApiResponse.ok(list);
    }
}
