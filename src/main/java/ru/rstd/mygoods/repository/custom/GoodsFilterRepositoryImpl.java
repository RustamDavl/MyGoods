package ru.rstd.mygoods.repository.custom;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.repository.predicates.QGoodsPredicate;

import java.util.List;

import static ru.rstd.mygoods.entity.QGoods.goods;

@RequiredArgsConstructor
public class GoodsFilterRepositoryImpl implements GoodsFilterRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Goods> findAllByFilter(GoodsFilter goodsFilter) {
        Predicate predicate = QGoodsPredicate.builder()
                .add(goodsFilter.getName(), o -> goods.name.containsIgnoreCase(goodsFilter.getName()))
                .add(goodsFilter.getPrice(), o -> {
                    if (goodsFilter.getOperation().equals("<")) {
                        return goods.price.loe(goodsFilter.getPrice());
                    }
                    if (goodsFilter.getOperation().equals(">")) {
                        return goods.price.goe(goodsFilter.getPrice());
                    } else return goods.price.eq(goodsFilter.getPrice());
                })
                .add(goodsFilter.isInStock(), o -> goods.inStock.eq(goodsFilter.isInStock()))
                .build();
        return jpaQueryFactory
                .select(goods)
                .from(goods)
                .where(predicate)
                .fetch();

    }

    @Override
    public List<Goods> findAllByFilterAndSort(GoodsFilter goodsFilter, Pageable pageable) {
        return null;
    }
}
