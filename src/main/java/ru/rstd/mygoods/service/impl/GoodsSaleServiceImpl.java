package ru.rstd.mygoods.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.entity.GoodsDelivery;
import ru.rstd.mygoods.entity.GoodsSale;
import ru.rstd.mygoods.exception.DocumentDuplicateException;
import ru.rstd.mygoods.exception.SaleDocumentNotFoundException;
import ru.rstd.mygoods.repository.GoodsSaleRepository;
import ru.rstd.mygoods.service.GoodsDeliveryService;
import ru.rstd.mygoods.service.GoodsSaleService;
import ru.rstd.mygoods.service.GoodsService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsSaleServiceImpl implements GoodsSaleService {
    private final GoodsSaleRepository goodsSaleRepository;
    private final GoodsService goodsService;
    private final GoodsDeliveryService goodsDeliveryService;


    @Override
    @Transactional
    public GoodsSale create(GoodsSale goodsSale, Long goodsId) {
        throwExIfDocExists(goodsId);
        throwExIfSalesAmountIncorrect(goodsSale, goodsId);
        Goods maybeGoods = goodsService.getById(goodsId);
        BigDecimal purchasePrice = maybeGoods.getPrice()
                .multiply(BigDecimal.valueOf(goodsSale.getAmount()));
        goodsSale.setGoods(maybeGoods);
        goodsSale.setPurchasePrice(purchasePrice);
        return goodsSaleRepository.save(goodsSale);
    }

    @Override
    public GoodsSale getById(Long id) {
        return goodsSaleRepository.findById(id)
                .orElseThrow(() -> new SaleDocumentNotFoundException("There is no sale document with such id."));
    }

    @Override
    public List<GoodsSale> getAll() {
        return goodsSaleRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        goodsSaleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public GoodsSale update(GoodsSale goodsSale, Long goodsId) {
        Goods maybeGoods = goodsService.getById(goodsId);
        GoodsSale maybeGoodsSale = goodsSaleRepository.findByGoodsId(goodsId)
                .orElseThrow(() -> new SaleDocumentNotFoundException("There is no sale document with such id."));
        BigDecimal finalPurchasePrice;
        if (!maybeGoods.getInStock()) {
            throw new IllegalArgumentException("Goods is not present.");
        } else {
            Integer delivered = goodsDeliveryService.getByGoodsId(goodsId).getAmount();
            Integer sold = maybeGoodsSale.getAmount();
            Integer remains = delivered - sold;
            if (goodsSale.getAmount() > remains)
                throw new IllegalArgumentException("There are fewer existing goods than purchased ones");
            else if (goodsSale.getAmount().intValue() == remains.intValue()) {
                maybeGoods.setInStock(false);
                maybeGoods = goodsService.update(maybeGoods);
            }
            goodsSale.setAmount(goodsSale.getAmount() + sold);
            finalPurchasePrice = BigDecimal.valueOf(goodsSale.getAmount()).multiply(maybeGoods.getPrice());
            goodsSale.setPurchasePrice(finalPurchasePrice);
        }
        goodsSale.setGoods(maybeGoods);
        return goodsSaleRepository.save(goodsSale);
    }

    private void throwExIfDocExists(Long goodsId) {
        Optional<GoodsSale> goodsOptional = goodsSaleRepository.findByGoodsId(goodsId);
        if (goodsOptional.isPresent())
            throw new DocumentDuplicateException("Goods sale document with such goods already exists.");
    }

    private void throwExIfSalesAmountIncorrect(GoodsSale goodsSale, Long goodsId) {
        GoodsDelivery maybeGoodsDelivery = goodsDeliveryService.getByGoodsId(goodsId);
        if (maybeGoodsDelivery.getAmount() < goodsSale.getAmount())
            throw new IllegalArgumentException("There are fewer existing goods than purchased ones");
        if (maybeGoodsDelivery.getAmount().intValue() == goodsSale.getAmount().intValue()) {
            goodsService.updateInStock(goodsId, false);
        }
    }
}
