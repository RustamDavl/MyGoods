package ru.rstd.mygoods.service.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.exception.GoodsNotFoundException;
import ru.rstd.mygoods.repository.GoodsRepository;
import ru.rstd.mygoods.repository.predicates.QGoodsPredicate;
import ru.rstd.mygoods.service.GoodsService;

import java.math.BigDecimal;
import java.util.List;

import static ru.rstd.mygoods.entity.QGoods.goods;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepository;

    @Override
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Page<Goods> getAllByFilterWithPageable(GoodsFilter goodsFilter, Pageable pageable) {
        Predicate predicate = preparePredicate(goodsFilter);
        return goodsRepository.findAll(predicate, pageable);
    }

    @Override
    public Goods getById(Long id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new GoodsNotFoundException("There is no goods with such id"));
    }

    @Override
    @Transactional
    public Goods create(Goods goods) {
        if (goods.getInStock() == null)
            goods.setInStock(false);
        if (goods.getPrice() == null)
            goods.setPrice(BigDecimal.valueOf(0));
        return goodsRepository.save(goods);
    }

    @Override
    @Transactional
    public Goods update(Goods goods) {
        goodsRepository.findById(goods.getId())
                .orElseThrow(() -> new GoodsNotFoundException("There is no goods with such id"));
        return goodsRepository.save(goods);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        goodsRepository.findById(id)
                .orElseThrow(() -> new GoodsNotFoundException("There is no goods with such id"));
        goodsRepository.deleteById(id);
    }

    private Predicate preparePredicate(GoodsFilter goodsFilter) {
        return QGoodsPredicate.builder()
                .add(goodsFilter.getName(), o -> goods.name.containsIgnoreCase(goodsFilter.getName()))
                .add(goodsFilter.getPrice(), o -> {
                    if(goodsFilter.getOp() == null)
                        return goods.price.eq(BigDecimal.valueOf(Double.parseDouble(goodsFilter.getPrice())));
                    if (goodsFilter.getOp().equals("<")) {
                        return goods.price.loe(BigDecimal.valueOf(Double.parseDouble(goodsFilter.getPrice())));
                    }
                    if (goodsFilter.getOp().equals(">")) {
                        return goods.price.goe(BigDecimal.valueOf(Double.parseDouble(goodsFilter.getPrice())));
                    } else return Expressions.asBoolean(true).isTrue();
                })
                .add(goodsFilter.getInStock(), o -> {
                    if (goodsFilter.getInStock() == null) {
                        return Expressions.asBoolean(true).isTrue();
                    } else
                        return goods.inStock.eq(Boolean.valueOf(goodsFilter.getInStock()));
                })
                .build();
    }
}
