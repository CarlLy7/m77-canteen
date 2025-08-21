package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.domain.param.CreateScoreParam;
import com.tencent.wxcloudrun.domain.param.OrderCreateParam;
import com.tencent.wxcloudrun.domain.vo.OrderVo;

import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 21:24
 * @Since: 1.0
 */
public interface OrderService {
    void createOrder(String openId,OrderCreateParam param);

    List<OrderVo> getOrder(String openId);

    void createScore(String openId, CreateScoreParam param);
}
