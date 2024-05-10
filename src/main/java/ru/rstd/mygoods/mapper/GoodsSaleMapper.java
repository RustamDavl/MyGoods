package ru.rstd.mygoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rstd.mygoods.dto.goodssale.CreateUpdateGoodsSaleDto;
import ru.rstd.mygoods.dto.goodssale.ReadGoodsSaleDto;
import ru.rstd.mygoods.entity.GoodsSale;

@Mapper(componentModel = "spring")
public interface GoodsSaleMapper {

    GoodsSale toEntity(CreateUpdateGoodsSaleDto dto);

    @Mapping(source = "goods", target = "readGoodsDto")
    ReadGoodsSaleDto toReadDto(GoodsSale entity);
}
