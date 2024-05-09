package ru.rstd.mygoods.integration.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.integration.IntegrationTestBase;
import ru.rstd.mygoods.service.GoodsService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@Sql(value = "classpath:sql/data.sql")
public class GoodsServiceIT extends IntegrationTestBase {

    private final GoodsService goodsService;

    @Test
    void findAll_By_Filter_And_Pageable() {
        GoodsFilter goodsFilter = GoodsFilter.builder()
                .name("a")
                .price("500")
                .op(">")
                .build();
        Pageable pageable = PageRequest.of(1, 15);
        Page<Goods> page = goodsService.getAllByFilterWithPageable(goodsFilter, pageable);
        assertThat(page).isNotNull();
        assertThat(page).isNotEmpty();
        assertThat(page.getTotalPages()).isEqualTo(25);
        assertThat(page.getTotalElements()).isEqualTo(363);
    }
}
