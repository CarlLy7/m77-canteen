package com.tencent.wxcloudrun.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.21
 * @Since: 1.0
 */
@Data
public class CreateScoreParam {
    @NotBlank(message = "订单编号不能为空")
    private String orderNo;

    @NotBlank(message = "评分不能为空")
    private String score;
}
