package com.tencent.wxcloudrun.domain.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-24 11:36
 * @Since: 1.0
 */
@Data
public class SubScribeParam {
    @NotNull(message = "模板id不能为空")
    @NotEmpty(message = "模板id不能为空")
    private List<String> templateIds;
}
