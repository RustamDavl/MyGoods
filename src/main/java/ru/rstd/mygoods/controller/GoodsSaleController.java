package ru.rstd.mygoods.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rstd.mygoods.dto.goodssale.CreateUpdateGoodsSaleDto;
import ru.rstd.mygoods.dto.goodssale.ReadGoodsSaleDto;
import ru.rstd.mygoods.dto.validation.group.OnCreate;
import ru.rstd.mygoods.dto.validation.group.OnUpdate;
import ru.rstd.mygoods.entity.GoodsSale;
import ru.rstd.mygoods.mapper.GoodsSaleMapper;
import ru.rstd.mygoods.service.GoodsSaleService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
public class GoodsSaleController {

    private final GoodsSaleService goodsSaleService;
    private final GoodsSaleMapper goodsSaleMapper;

    @GetMapping
    public ResponseEntity<List<ReadGoodsSaleDto>> getAll() {
        List<ReadGoodsSaleDto> readGoodsDeliveryDtos = goodsSaleService.getAll()
                .stream()
                .map(goodsSaleMapper::toReadDto)
                .toList();
        return ResponseEntity.status(OK)
                .body(readGoodsDeliveryDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadGoodsSaleDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK)
                .body(goodsSaleMapper.toReadDto(goodsSaleService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ReadGoodsSaleDto> create(@Validated(OnCreate.class) @RequestBody CreateUpdateGoodsSaleDto createUpdateGoodsSaleDto) {
        GoodsSale goods = goodsSaleMapper.toEntity(createUpdateGoodsSaleDto);
        return ResponseEntity.status(CREATED)
                .body(goodsSaleMapper.toReadDto(
                        goodsSaleService.create(goods, createUpdateGoodsSaleDto.getGoodsId())));
    }

    @PutMapping
    public ResponseEntity<ReadGoodsSaleDto> update(@RequestBody @Validated(OnUpdate.class) CreateUpdateGoodsSaleDto createUpdateGoodsSaleDto) {
        GoodsSale goods = goodsSaleMapper.toEntity(createUpdateGoodsSaleDto);
        return ResponseEntity.status(CREATED)
                .body(goodsSaleMapper.toReadDto(
                        goodsSaleService.update(goods, createUpdateGoodsSaleDto.getGoodsId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        goodsSaleService.delete(id);
        return ResponseEntity.status(OK)
                .build();
    }

}
