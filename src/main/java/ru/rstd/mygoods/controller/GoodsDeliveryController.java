package ru.rstd.mygoods.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rstd.mygoods.dto.goods.CreateUpdateGoodsDto;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.dto.goods.GoodsPageResponse;
import ru.rstd.mygoods.dto.goods.ReadGoodsDto;
import ru.rstd.mygoods.dto.goodsdelivery.CreateUpdateGoodsDeliveryDto;
import ru.rstd.mygoods.dto.goodsdelivery.ReadGoodsDeliveryDto;
import ru.rstd.mygoods.dto.validation.group.OnCreate;
import ru.rstd.mygoods.dto.validation.group.OnUpdate;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.entity.GoodsDelivery;
import ru.rstd.mygoods.mapper.GoodDeliveryMapper;
import ru.rstd.mygoods.service.GoodsDeliveryService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class GoodsDeliveryController {

    private final GoodsDeliveryService goodsDeliveryService;
    private final GoodDeliveryMapper goodDeliveryMapper;

    @GetMapping
    public ResponseEntity<List<ReadGoodsDeliveryDto>> getAll() {
        List<ReadGoodsDeliveryDto> readGoodsDeliveryDtos = goodsDeliveryService.getAll()
                .stream()
                .map(goodDeliveryMapper::toReadDto)
                .toList();
        return ResponseEntity.status(OK)
                .body(readGoodsDeliveryDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadGoodsDeliveryDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK)
                .body(goodDeliveryMapper.toReadDto(goodsDeliveryService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<GoodsDelivery> create(@Validated(OnCreate.class) @RequestBody CreateUpdateGoodsDeliveryDto createUpdateGoodsDeliveryDto) {
        GoodsDelivery goods = goodDeliveryMapper.toEntity(createUpdateGoodsDeliveryDto);
        return ResponseEntity.status(CREATED)
                .body(goodsDeliveryService.create(goods, createUpdateGoodsDeliveryDto.getGoodsId()));
    }

    @PutMapping
    public ResponseEntity<GoodsDelivery> update(@RequestBody @Validated(OnUpdate.class) CreateUpdateGoodsDeliveryDto createUpdateGoodsDeliveryDto) {
        GoodsDelivery goods = goodDeliveryMapper.toEntity(createUpdateGoodsDeliveryDto);
        return ResponseEntity.status(CREATED)
                .body(goodsDeliveryService.update(goods, createUpdateGoodsDeliveryDto.getGoodsId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        goodsDeliveryService.delete(id);
        return ResponseEntity.status(OK)
                .build();
    }

}
