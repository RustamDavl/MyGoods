package ru.rstd.mygoods.dto.goodssale;

import lombok.Data;
import ru.rstd.mygoods.dto.goods.ReadGoodsDto;

@Data
public class ReadGoodsSaleDto {

    Long id;
    String name;
    ReadGoodsDto readGoodsDto;
    String amount;
    String purchasePrice;
}
