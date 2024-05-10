package ru.rstd.mygoods.dto.goodsdelivery;

import lombok.Builder;
import lombok.Data;
import ru.rstd.mygoods.dto.goods.ReadGoodsDto;

@Data
@Builder
public class ReadGoodsDeliveryDto {

    Long id;
    String name;
    ReadGoodsDto readGoodsDto;
    String amount;
}
