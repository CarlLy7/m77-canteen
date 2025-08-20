package com.tencent.wxcloudrun.domain.convert;

import com.tencent.wxcloudrun.domain.vo.SkuInfoVo;
import com.tencent.wxcloudrun.model.BbProductSku;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.20
 * @Since: 1.0
 */
@Mapper
public interface ProductSkuConvert {
    ProductSkuConvert INSTANCE=Mappers.getMapper(ProductSkuConvert.class);


    SkuInfoVo bbProductSkuToSkuInfoVo(BbProductSku bbProductSku);

    List<SkuInfoVo> bbProductSkusToSkuInfoVos(List<BbProductSku> bbProductSkus);

}
