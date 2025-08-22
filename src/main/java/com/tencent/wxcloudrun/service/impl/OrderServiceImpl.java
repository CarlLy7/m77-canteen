package com.tencent.wxcloudrun.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tencent.wxcloudrun.domain.convert.OrderConvert;
import com.tencent.wxcloudrun.domain.param.CreateScoreParam;
import com.tencent.wxcloudrun.domain.param.OrderCreateParam;
import com.tencent.wxcloudrun.domain.param.OrderItemParam;
import com.tencent.wxcloudrun.domain.param.OrderQueryParam;
import com.tencent.wxcloudrun.domain.vo.OrderDtlVo;
import com.tencent.wxcloudrun.domain.vo.OrderVo;
import com.tencent.wxcloudrun.model.BbOrder;
import com.tencent.wxcloudrun.model.BbOrderDtl;
import com.tencent.wxcloudrun.service.OrderService;
import com.tencent.wxcloudrun.service.base.BbOrderDtlService;
import com.tencent.wxcloudrun.service.base.BbOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-18 21:24
 * @Since: 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private BbOrderService bbOrderService;

    @Resource
    private BbOrderDtlService bbOrderDtlService;

    @Value("${order.createTemplateId}")
    private String templateId;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(String openId, OrderCreateParam param) {
        List<OrderItemParam> items = param.getItems();
        BbOrder bbOrder = new BbOrder();
        String orderNo = IdUtil.getSnowflakeNextIdStr();
        bbOrder.setOrderNo(orderNo);
        bbOrder.setCreateTime(LocalDateTime.now());
        bbOrder.setCreatorBy(openId);
        List<BbOrderDtl> orderDtls = new ArrayList<>();
        for (OrderItemParam item : items) {
            BbOrderDtl bbOrderDtl = new BbOrderDtl();
            bbOrderDtl.setProductId(item.getProductId());
            bbOrderDtl.setSkuId(item.getSkuId());
            bbOrderDtl.setOrderNo(orderNo);
            bbOrderDtl.setSkuName(item.getSkuName());
            bbOrderDtl.setCount(item.getCountNum());
            bbOrderDtl.setImageUrl(item.getImageUrl());
            bbOrderDtl.setPrice(item.getPrice());
            bbOrderDtl.setProductName(item.getProductName());
            orderDtls.add(bbOrderDtl);
        }
        bbOrderService.save(bbOrder);
        bbOrderDtlService.saveBatch(orderDtls);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                pushMessage(openId, orderNo, param.getItems().get(0));
            }
        });
    }

    /**
     * 下单成功消息推送
     *
     * @param openId
     * @param orderNo
     * @param orderItem
     * @param createTime
     */
    private void pushMessage(String openId, String orderNo, OrderItemParam orderItem) {
        JSONObject body = new JSONObject();
        body.set("template_id", templateId);
        body.set("touser", openId);
        body.set("miniprogram_state", "developer");
        body.set("page","/pages/order/order?orderNo="+orderNo);
        body.set("lang", "zh_CN");
        JSONObject data = new JSONObject();
        data.set("character_string1", new JSONObject().set("value", orderNo));
        String content = orderItem.getProductName() + "x" + orderItem.getCountNum();
        data.set("thing2", new JSONObject().set("value", content));
        data.set("time4", new JSONObject().set("value", DateUtil.format(DateUtil.date(), "yyyy年MM月dd日 HH:mm")));
        data.set("phrase5", new JSONObject().set("value", "下单成功"));
        data.set("thing9", new JSONObject().set("value", "宝宝肚肚打雷了"));
        body.set("data", data);
        log.info("请求数据=[{}]", body.toString());
        HttpResponse response = HttpRequest.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send")
                .body(body.toString())
                .execute();
        log.info("pushMessage result= [{}]", response.body());

        System.out.println(response.body());
    }

    @Override
    public List<OrderVo> getOrder(String openId, OrderQueryParam param) {
        String today = DateUtil.today();
        DateTime oneMonthBefore = DateUtil.beginOfMonth(DateUtil.date());
        QueryWrapper<BbOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator_by", openId);
        queryWrapper.ge("DATE_FORMAT(create_time,'%Y-%m-%d')", oneMonthBefore);
        queryWrapper.le("DATE_FORMAT(create_time,'%Y-%m-%d')", today);
        queryWrapper.eq(StrUtil.isNotBlank(param.getOrderNo()),"order_no",param.getOrderNo());
        if (StrUtil.isNotBlank(param.getProductName())) {
            queryWrapper.exists(" select 1 from bb_order_dtl d where d.order_no=bb_order.order_no " +
                    " and d.product_name like {0} ", "%" + param.getProductName() + "%");
        }
        List<BbOrder> orderList = bbOrderService.list(queryWrapper);
        if (CollUtil.isEmpty(orderList)) {
            return new ArrayList<>();
        }
        List<OrderVo> result = new ArrayList<>();
        for (BbOrder bbOrder : orderList) {
            OrderVo orderVo = new OrderVo();
            orderVo.setOrderNo(bbOrder.getOrderNo());
            orderVo.setCreateTime(bbOrder.getCreateTime());
            orderVo.setScore(bbOrder.getScore());
            result.add(orderVo);
        }

        //查询子表
        List<String> orderNoList = orderList.stream().map(BbOrder::getOrderNo).collect(Collectors.toList());
        List<BbOrderDtl> orderDtlList = bbOrderDtlService.list(Wrappers.lambdaQuery(BbOrderDtl.class)
                .in(BbOrderDtl::getOrderNo, orderNoList));
        if (CollUtil.isNotEmpty(orderDtlList)) {
            //根据订单编号分组
            Map<String, List<BbOrderDtl>> orderDtlGroup = orderDtlList.stream()
                    .collect(Collectors.groupingBy(BbOrderDtl::getOrderNo));
            for (OrderVo orderVo : result) {
                String orderNo = orderVo.getOrderNo();
                if (!orderDtlGroup.containsKey(orderNo)) {
                    continue;
                }
                List<BbOrderDtl> orderDtls = orderDtlGroup.get(orderNo);
                List<OrderDtlVo> orderDtlVos = OrderConvert.INSTANCE.bbOrderDtlsToBbOrderDtls(orderDtls);
                orderVo.setItems(orderDtlVos);
            }
        }else{
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public void createScore(String openId, CreateScoreParam param) {
        BbOrder one = bbOrderService.getOne(Wrappers.lambdaQuery(BbOrder.class)
                .eq(BbOrder::getCreatorBy, openId)
                .eq(BbOrder::getOrderNo, param.getOrderNo()));
        Assert.notNull(one, "订单不存在");
        if (Objects.nonNull(one.getScore())) {
            throw new RuntimeException("不可以重复评分");
        }
        bbOrderService.update(Wrappers.lambdaUpdate(BbOrder.class)
                .eq(BbOrder::getCreatorBy, openId)
                .eq(BbOrder::getOrderNo, param.getOrderNo())
                .set(BbOrder::getScore, param.getScore()));
    }


}
