package ru.rstd.mygoods.dto.goods;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsFilter {
    private String name;
    private BigDecimal price;
    private String operation;
    private boolean inStock;
}
