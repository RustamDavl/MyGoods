package ru.rstd.mygoods.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.exception.GoodsNotFoundException;
import ru.rstd.mygoods.repository.GoodsRepository;
import ru.rstd.mygoods.service.GoodsService;

import java.math.BigDecimal;
import java.util.List;

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
}
