package ru.rstd.mygoods.mapper;

import org.mapstruct.Mapper;
import ru.rstd.mygoods.dto.goods.CreateUpdateGoodsDto;
import ru.rstd.mygoods.dto.goods.ReadGoodsDto;
import ru.rstd.mygoods.entity.Goods;

@Mapper(componentModel = "spring")
public interface GoodsMapper {
    Goods toEntity(CreateUpdateGoodsDto dto);

    ReadGoodsDto toReadGoodsDto(Goods goods);
}
