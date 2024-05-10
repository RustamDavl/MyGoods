package ru.rstd.mygoods.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.entity.GoodsDelivery;
import ru.rstd.mygoods.entity.GoodsSale;
import ru.rstd.mygoods.exception.DeliveryDocumentNotFoundException;
import ru.rstd.mygoods.exception.DocumentDuplicateException;
import ru.rstd.mygoods.repository.GoodsDeliveryRepository;
import ru.rstd.mygoods.service.GoodsDeliveryService;
import ru.rstd.mygoods.service.GoodsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsDeliveryServiceImpl implements GoodsDeliveryService {

    private final GoodsDeliveryRepository goodsDeliveryRepository;
    private final GoodsService goodsService;

    @Override
    public GoodsDelivery create(GoodsDelivery goodsDelivery, Long goodsId) {
        Optional<GoodsDelivery> goodsOptional = goodsDeliveryRepository.findByGoodsId(goodsId);
        if (goodsOptional.isPresent())
            throw new DocumentDuplicateException("Goods sale document with such goods already exists.");
        Goods maybeGoods = goodsService.getById(goodsId);
        if (!maybeGoods.getInStock())
            maybeGoods.setInStock(true);
        goodsDelivery.setGoods(maybeGoods);
        return goodsDeliveryRepository.save(goodsDelivery);
    }

    @Override
    public GoodsDelivery getById(Long id) {
        return goodsDeliveryRepository.findById(id).orElseThrow(() ->
                new DeliveryDocumentNotFoundException("There is no document with such id."));
    }

    @Override
    public List<GoodsDelivery> getAll() {
        return goodsDeliveryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        goodsDeliveryRepository.deleteById(id);
    }

    @Override
    public GoodsDelivery update(GoodsDelivery goodsDelivery, Long goodsId) {
        goodsDeliveryRepository.findById(goodsId).orElseThrow(() -> new DeliveryDocumentNotFoundException("There is no document with such id."));
        Goods maybeGoods = goodsService.getById(goodsId);
        goodsDelivery.setGoods(maybeGoods);
        return goodsDeliveryRepository.save(goodsDelivery);
    }

    @Override
    public GoodsDelivery getByGoodsId(Long goodsId) {
        return goodsDeliveryRepository.findByGoodsId(goodsId)
                .orElseThrow(() -> new DeliveryDocumentNotFoundException("There is no delivery document with such goods"));
    }
}
