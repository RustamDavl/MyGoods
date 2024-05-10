package ru.rstd.mygoods.service;

import ru.rstd.mygoods.entity.GoodsDelivery;

import java.util.List;
import java.util.Optional;

public interface GoodsDeliveryService {

    GoodsDelivery create(GoodsDelivery goodsDelivery, Long goodsId);
    GoodsDelivery getById(Long id);

    List<GoodsDelivery> getAll();

    void delete(Long id);
    GoodsDelivery update(GoodsDelivery goodsDelivery, Long goodsId);
    GoodsDelivery getByGoodsId(Long goodsId);
}
