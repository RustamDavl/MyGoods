package ru.rstd.mygoods.dto.goods;

import lombok.Data;

@Data
public class ReadGoodsDto {

    private Long id;
    private String name;
    private String description;
    private boolean inStock;
    private String price;
}
