package com.tencent.wxcloudrun.domain.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-20 21:04
 * @Since: 1.0
 */
@Data
public class OrderCreateParam {

    /**
     * 主厨id
     */
    @NotNull(message = "主厨不可以为空")
    private Integer canteenId;

    @NotNull(message = "子项不能为空")
    @NotEmpty(message = "子项不能为空")
    private List<OrderItemParam> items;
}
