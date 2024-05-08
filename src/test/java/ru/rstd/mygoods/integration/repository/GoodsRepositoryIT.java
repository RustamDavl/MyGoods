package ru.rstd.mygoods.integration.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.integration.IntegrationTestBase;
import ru.rstd.mygoods.repository.GoodsRepository;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Sql(value = "classpath:sql/data.sql")
public class GoodsRepositoryIT extends IntegrationTestBase {
    private final GoodsRepository goodsRepository;

    @Test
    void findAll_with_pageable() {
        GoodsFilter goodsFilter = new GoodsFilter();
        goodsFilter.setName("Ab");
        goodsFilter.setInStock(true);
        goodsFilter.setOperation(">");
        goodsFilter.setPrice(BigDecimal.valueOf(535));
        var goods = goodsRepository.findAllByFilter(goodsFilter);
        goods.forEach(goods1 -> {
            System.out.println(goods1.getName() + " " + goods1.getPrice() + " is in stock = " + goods1.getInStock());
        });
    }

}
