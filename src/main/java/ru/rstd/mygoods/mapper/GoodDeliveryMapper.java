package ru.rstd.mygoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rstd.mygoods.dto.goodsdelivery.CreateUpdateGoodsDeliveryDto;
import ru.rstd.mygoods.dto.goodsdelivery.ReadGoodsDeliveryDto;
import ru.rstd.mygoods.entity.GoodsDelivery;

@Mapper(componentModel = "spring")
public interface GoodDeliveryMapper {

    GoodsDelivery toEntity(CreateUpdateGoodsDeliveryDto dto);

    @Mapping(source = "goods", target = "readGoodsDto")
    ReadGoodsDeliveryDto toReadDto(GoodsDelivery entity);
}
