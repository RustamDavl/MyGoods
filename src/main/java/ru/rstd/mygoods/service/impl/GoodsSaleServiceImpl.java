package ru.rstd.mygoods.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
public class GoodsSaleServiceImpl implements GoodsSaleService {
    private final GoodsSaleRepository goodsSaleRepository;
    private final GoodsService goodsService;


    @Override
    public GoodsSale create(GoodsSale goodsSale, Long goodsId) {
        Optional<GoodsSale> goodsOptional = goodsSaleRepository.findByGoodsId(goodsId);
        if(goodsOptional.isPresent())
            throw new DocumentDuplicateException("Goods sale document with such goods already exists.");
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
    public void delete(Long id) {
        goodsSaleRepository.deleteById(id);
    }

    @Override
    public GoodsSale update(GoodsSale goodsSale, Long goodsId) {
        Goods maybeGoods = goodsService.getById(goodsId);
        goodsSale.setGoods(maybeGoods);
        return goodsSaleRepository.save(goodsSale);
    }
}
