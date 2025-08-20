package com.tencent.wxcloudrun.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-20 21:18
 * @Since: 1.0
 */
@Data
public class OrderVo {
    private String orderNo;

    private LocalDateTime createTime;


    private List<OrderDtlVo> items;
}
