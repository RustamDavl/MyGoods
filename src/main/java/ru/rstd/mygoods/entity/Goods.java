package ru.rstd.mygoods.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Goods {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean inStock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
