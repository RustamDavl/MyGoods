package ru.rstd.mygoods.service;

import ru.rstd.mygoods.entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> getAll();
    Goods getById(Long id);

    Goods create(Goods goods);

    Goods update(Goods goods);

    void delete(Long id);
}
