package ru.rstd.mygoods.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rstd.mygoods.dto.goods.CreateUpdateGoodsDto;
import ru.rstd.mygoods.dto.goods.GoodsFilter;
import ru.rstd.mygoods.dto.goods.GoodsPageResponse;
import ru.rstd.mygoods.dto.goods.ReadGoodsDto;
import ru.rstd.mygoods.dto.validation.group.OnCreate;
import ru.rstd.mygoods.dto.validation.group.OnUpdate;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.mapper.GoodsMapper;
import ru.rstd.mygoods.service.GoodsService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;
    private final GoodsMapper goodsMapper;

    @GetMapping
    public ResponseEntity<GoodsPageResponse<Goods>> getAll(@Valid GoodsFilter goodsFilter, Pageable pageable) {
        Page<Goods> page = goodsService.getAllByFilterWithPageable(goodsFilter, pageable);
        return ResponseEntity.status(OK)
                .body(GoodsPageResponse.of(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadGoodsDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK)
                .body(goodsMapper.toReadGoodsDto(goodsService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Goods> create(@Validated(OnCreate.class) @RequestBody CreateUpdateGoodsDto createUpdateGoodsDto) {
        Goods goods = goodsMapper.toEntity(createUpdateGoodsDto);
        return ResponseEntity.status(CREATED)
                .body(goodsService.create(goods));
    }

    @PutMapping
    public ResponseEntity<Goods> update(@RequestBody @Validated(OnUpdate.class) CreateUpdateGoodsDto createUpdateGoodsDto) {
        Goods goods = goodsMapper.toEntity(createUpdateGoodsDto);
        return ResponseEntity.status(CREATED)
                .body(goodsService.update(goods));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        goodsService.delete(id);
        return ResponseEntity.status(OK)
                .build();
    }
}
