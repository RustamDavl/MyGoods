package ru.rstd.mygoods.unit.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.exception.GoodsNotFoundException;
import ru.rstd.mygoods.repository.GoodsRepository;
import ru.rstd.mygoods.service.impl.GoodsServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class GoodsServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsServiceImpl goodsService;


    @Test
    @DisplayName("create by id should pass")
    void getById_should_pass() {
        Goods savedGoods = getSavedGoods();

        doReturn(Optional.of(savedGoods)).when(goodsRepository).findById(1L);

        Goods actualResult = goodsService.getById(1L);

        assertThat(actualResult.getName()).isEqualTo(savedGoods.getName());
        assertThat(actualResult.getDescription()).isEqualTo(savedGoods.getDescription());
        assertThat(actualResult.getId()).isEqualTo(savedGoods.getId());
        assertThat(actualResult.getPrice()).isEqualTo(savedGoods.getPrice());

    }

    @Test
    @DisplayName("create by id should throw GoodsNotFoundException")
    void getById_should_throw_GoodsNotFoundException() {
        doThrow(GoodsNotFoundException.class).when(goodsRepository).findById(1L);

        assertThrows(GoodsNotFoundException.class, () -> goodsService.getById(1L));
    }

    @Test
    void create() {
        Goods goodsFromRequest = getGoodsFromRequest();
        Goods savedGoods = getSavedGoods();

        doReturn(savedGoods).when(goodsRepository).create(goodsFromRequest);

        Goods actualResult = goodsService.create(goodsFromRequest);
        assertThat(actualResult.getId()).isEqualTo(savedGoods.getId());
    }

    @Test
    void update() {
        Goods oldGoods = getSavedGoods();
        Goods newGoods = Goods.builder()
                .id(1L)
                .name("BrainDance")
                .description("some new")
                .price(BigDecimal.valueOf(11111.11))
                .inStock(true)
                .build();
        doReturn(newGoods).when(goodsRepository).update(oldGoods);

        Goods actualResult = goodsService.update(newGoods);

        assertThat(actualResult.getId()).isEqualTo(newGoods.getId());
        assertThat(actualResult.getName()).isEqualTo(newGoods.getName());
        assertThat(actualResult.getDescription()).isEqualTo(newGoods.getDescription());
        assertThat(actualResult.getPrice()).isEqualTo(newGoods.getPrice());
        assertThat(actualResult.getInStock()).isEqualTo(newGoods.getInStock());
    }

    private Goods getGoodsFromRequest() {
        return Goods.builder()
                .id(1L)
                .name("BrainDance")
                .description("Everything on full blast.")
                .price(BigDecimal.valueOf(1000.00))
                .inStock(true)
                .build();
    }

    private Goods getSavedGoods() {
        return Goods.builder()
                .id(1L)
                .name("BrainDance")
                .description("Everything on full blast.")
                .price(BigDecimal.valueOf(1000.00))
                .inStock(true)
                .build();
    }

}
