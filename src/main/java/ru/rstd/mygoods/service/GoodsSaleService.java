package ru.rstd.mygoods.service;

import ru.rstd.mygoods.entity.GoodsDelivery;
import ru.rstd.mygoods.entity.GoodsSale;

import java.util.List;
import java.util.Optional;

public interface GoodsSaleService {

    GoodsSale create(GoodsSale goodsSale, Long goodsId);
    GoodsSale getById(Long id);

    List<GoodsSale> getAll();

    void delete(Long id);
    GoodsSale update(GoodsSale goodsSale, Long goodsId);
}
