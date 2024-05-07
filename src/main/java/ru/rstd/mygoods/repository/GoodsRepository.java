package ru.rstd.mygoods.repository;

import ru.rstd.mygoods.entity.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository {

    Optional<Goods> findById(Long id);

    List<Goods> findAll();

    Goods create(Goods goods);

    Goods update(Goods goods);

    void delete(Long id);
}
