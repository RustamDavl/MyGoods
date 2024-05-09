package ru.rstd.mygoods.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> getAll();
    Page<Goods> getAllByFilterWithPageable(GoodsFilter goodsFilter, Pageable pageable);
    Goods getById(Long id);

    Goods create(Goods goods);

    Goods update(Goods goods);

    void delete(Long id);
}
