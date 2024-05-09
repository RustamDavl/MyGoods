package ru.rstd.mygoods.dto.goods;

import lombok.Builder;
import lombok.Data;
import ru.rstd.mygoods.dto.validation.annotation.InStock;
import ru.rstd.mygoods.dto.validation.annotation.NameFilter;
import ru.rstd.mygoods.dto.validation.annotation.OperationFilter;
import ru.rstd.mygoods.dto.validation.annotation.ValidPrice;

@Data
@Builder
public class GoodsFilter {

    @NameFilter
    private String name;
    @ValidPrice
    private String price;
    @OperationFilter
    private String op;
    @InStock
    private String inStock;
}
