package ru.rstd.mygoods.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.entity.GoodsTable;
import ru.rstd.mygoods.entity.Table;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GoodsTableTest {

    private static Table<Goods> table;

    @BeforeAll
    static void setUp() {
        table = new GoodsTable();
        prepareData();
    }

    @Test
    void selectById() {
        Goods actualResult = table.selectById(2L);
        assertThat(actualResult.getName()).isEqualTo("car");
        assertThat(actualResult.getDescription()).isEqualTo("yellow");
        assertThat(actualResult.getPrice()).isEqualTo(BigDecimal.valueOf(5000));
        assertThat(actualResult.getInStock()).isEqualTo(false);
    }

    @Test
    void selectAll() {
        int expectedListSize = 3;
        List<Goods> actualResult = table.selectAll();
        assertThat(actualResult).isNotEmpty();
        assertThat(actualResult.size()).isEqualTo(expectedListSize);
    }

    @Test
    void update() {
        Goods goodsFromRequest = Goods.builder()
                .id(2L)
                .name("car")
                .description("yellow")
                .price(BigDecimal.valueOf(10000))
                .inStock(true)
                .build();
        table.update(goodsFromRequest);
        Goods actualResult = table.selectById(2L);
        assertThat(actualResult.getId()).isEqualTo(2L);
        assertThat(actualResult.getName()).isEqualTo(goodsFromRequest.getName());
        assertThat(actualResult.getDescription()).isEqualTo(goodsFromRequest.getDescription());
        assertThat(actualResult.getPrice()).isEqualTo(goodsFromRequest.getPrice());
        assertThat(actualResult.getInStock()).isEqualTo(goodsFromRequest.getInStock());
    }

    @Test
    void insert() {
        Goods goods = Goods.builder()
                .name("new goods")
                .description("crazy goods")
                .price(BigDecimal.valueOf(9999))
                .inStock(true)
                .build();
        Long id = table.insert(goods);
        Goods actualResult = table.selectById(id);
        assertThat(actualResult.getId()).isEqualTo(id);
        assertThat(actualResult.getName()).isEqualTo(goods.getName());
        assertThat(actualResult.getDescription()).isEqualTo(goods.getDescription());
        assertThat(actualResult.getPrice()).isEqualTo(goods.getPrice());
    }

    @Test
    void delete() {
        table.delete(4L);
        table.delete(3L);
        List<Goods> goods = table.selectAll();
        assertThat(goods.size()).isEqualTo(2);
    }


    private static void prepareData() {
        Goods tv = Goods.builder()
                .name("TV")
                .description("Amoled")
                .price(BigDecimal.valueOf(1000))
                .inStock(true)
                .build();
        Goods car = Goods.builder()
                .name("car")
                .description("yellow")
                .price(BigDecimal.valueOf(5000))
                .inStock(false)
                .build();
        Goods fridge = Goods.builder()
                .name("fridge")
                .description("cool")
                .price(BigDecimal.valueOf(2000))
                .inStock(true)
                .build();
        table.insert(tv);
        table.insert(car);
        table.insert(fridge);
    }
}
