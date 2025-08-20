package com.tencent.wxcloudrun.domain.convert;

import com.tencent.wxcloudrun.domain.vo.OrderDtlVo;
import com.tencent.wxcloudrun.model.BbOrderDtl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-08-20 21:51
 * @Since: 1.0
 */
@Mapper
public interface OrderConvert {
    OrderConvert INSTANCE= Mappers.getMapper(OrderConvert.class);

    OrderDtlVo bbOrderDtlToBbOrderDtl(BbOrderDtl bbOrderDtl);

    List<OrderDtlVo> bbOrderDtlsToBbOrderDtls(List<BbOrderDtl> bbOrderDtls);
}
