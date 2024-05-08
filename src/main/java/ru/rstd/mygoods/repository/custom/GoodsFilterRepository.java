package ru.rstd.mygoods.repository.custom;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.entity.Goods;

import java.util.List;

@Repository
public interface GoodsFilterRepository {

    List<Goods> findAllByFilter(GoodsFilter goodsFilter);

    List<Goods> findAllByFilterAndSort(GoodsFilter goodsFilter, Pageable pageable);
}
