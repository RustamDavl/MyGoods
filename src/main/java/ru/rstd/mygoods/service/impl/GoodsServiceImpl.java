package ru.rstd.mygoods.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.exception.GoodsNotFoundException;
import ru.rstd.mygoods.repository.GoodsRepository;
import ru.rstd.mygoods.service.GoodsService;

import java.util.List;

@RequiredArgsConstructor
@Service
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
    public Goods create(Goods goods) {
        if (goods.getInStock() == null)
            goods.setInStock(false);
        return goodsRepository.create(goods);
    }

    @Override
    public Goods update(Goods goods) {
        return goodsRepository.update(goods);
    }

    @Override
    public void delete(Long id) {
        goodsRepository.delete(id);
    }
}
