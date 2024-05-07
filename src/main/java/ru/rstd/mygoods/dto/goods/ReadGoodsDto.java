package ru.rstd.mygoods.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReadGoodsDto {

    private Long id;
    private String name;
    private String description;
    private boolean inStock;
    private String price;
}
