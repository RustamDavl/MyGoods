package ru.rstd.mygoods.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class GoodsSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    private Integer amount;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;
}
