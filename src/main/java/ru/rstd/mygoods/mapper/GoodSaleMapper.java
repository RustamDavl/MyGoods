package ru.rstd.mygoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rstd.mygoods.dto.goodsdelivery.CreateUpdateGoodsDeliveryDto;
import ru.rstd.mygoods.dto.goodsdelivery.ReadGoodsDeliveryDto;
import ru.rstd.mygoods.dto.goodssale.CreateUpdateGoodsSaleDto;
import ru.rstd.mygoods.dto.goodssale.ReadGoodsSaleDto;
import ru.rstd.mygoods.entity.GoodsDelivery;
import ru.rstd.mygoods.entity.GoodsSale;

@Mapper(componentModel = "spring")
public interface GoodSaleMapper {

    GoodsSale toEntity(CreateUpdateGoodsSaleDto dto);

    @Mapping(source = "goods", target = "readGoodsDto")
    ReadGoodsSaleDto toReadDto(GoodsSale entity);
}
