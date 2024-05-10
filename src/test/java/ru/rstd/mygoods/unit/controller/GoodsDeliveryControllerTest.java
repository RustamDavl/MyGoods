package ru.rstd.mygoods.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.rstd.mygoods.controller.ControllerAdvice;
import ru.rstd.mygoods.controller.GoodsController;
import ru.rstd.mygoods.controller.GoodsDeliveryController;
import ru.rstd.mygoods.dto.goods.CreateUpdateGoodsDto;
import ru.rstd.mygoods.dto.goods.ReadGoodsDto;
import ru.rstd.mygoods.dto.goodsdelivery.CreateUpdateGoodsDeliveryDto;
import ru.rstd.mygoods.dto.goodsdelivery.ReadGoodsDeliveryDto;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.entity.GoodsDelivery;
import ru.rstd.mygoods.exception.DeliveryDocumentNotFoundException;
import ru.rstd.mygoods.exception.GoodsNotFoundException;
import ru.rstd.mygoods.mapper.GoodDeliveryMapper;
import ru.rstd.mygoods.mapper.GoodDeliveryMapperImpl;
import ru.rstd.mygoods.mapper.GoodsMapperImpl;
import ru.rstd.mygoods.service.GoodsDeliveryService;
import ru.rstd.mygoods.service.impl.GoodsDeliveryServiceImpl;
import ru.rstd.mygoods.service.impl.GoodsServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GoodsDeliveryControllerTest {

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private GoodDeliveryMapper goodDeliveryMapper;
    private GoodsDeliveryService goodsDeliveryService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        goodsDeliveryService = Mockito.mock(GoodsDeliveryServiceImpl.class);
        goodDeliveryMapper = Mockito.mock(GoodDeliveryMapperImpl.class);
        Mockito.mock(ControllerAdvice.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new GoodsDeliveryController(goodsDeliveryService, goodDeliveryMapper))
                .setControllerAdvice(new ControllerAdvice())
                .build();
    }

    @Test
    void getById_should_pass() throws Exception {
        ReadGoodsDto readGoodsDto = ReadGoodsDto.builder()
                .id(1L)
                .name("BrainDance")
                .description("ss")
                .inStock(true)
                .price("1000")
                .build();
        ReadGoodsDeliveryDto readGoodsDeliveryDto = ReadGoodsDeliveryDto.builder()
                .id(1L)
                .name("BrainDance")
                .readGoodsDto(readGoodsDto)
                .amount("10")
                .build();
        Goods goods = Goods.builder()
                .id(1L)
                .name("BrainDance")
                .description("Everything on full blast.")
                .price(BigDecimal.valueOf(10000))
                .inStock(true)
                .build();

        GoodsDelivery goodsDelivery = GoodsDelivery.builder()
                .id(1L)
                .name("BrainDance")
                .goods(goods)
                .amount(10)
                .build();

        doReturn(readGoodsDeliveryDto).when(goodDeliveryMapper).toReadDto(goodsDelivery);
        doReturn(goodsDelivery).when(goodsDeliveryService).getById(1L);
        mockMvc.perform(get("/api/v1/delivery/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("find by id should throw DeliveryDocumentNotFoundException")
    void getById_should_throw_DeliveryDocumentNotFoundException() throws Exception {
        doThrow(DeliveryDocumentNotFoundException.class).when(goodsDeliveryService).getById(1L);

        mockMvc.perform(get("/api/v1/delivery/{id}", 1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DeliveryDocumentNotFoundException));
    }

    @Test
    @DisplayName("creating goods should pass")
    void create_should_pass() throws Exception {
        CreateUpdateGoodsDeliveryDto createUpdateGoodsDeliveryDto = CreateUpdateGoodsDeliveryDto.builder()
                .goodsId(1L)
                .amount(10)
                .name("BrainDance")
                .build();

        Goods goods = Goods.builder()
                .id(1L)
                .name("BrainDance")
                .description("Everything on full blast.")
                .price(BigDecimal.valueOf(10000))
                .inStock(true)
                .build();

        GoodsDelivery goodsDelivery = GoodsDelivery.builder()
                .name("BrainDance")
                .goods(goods)
                .amount(10)
                .build();
        GoodsDelivery createdGoodsDelivery = GoodsDelivery.builder()
                .id(1L)
                .name("BrainDance")
                .goods(goods)
                .amount(10)
                .build();

        String json = objectMapper.writeValueAsString(createUpdateGoodsDeliveryDto);

        doReturn(goodsDelivery).when(goodDeliveryMapper).toEntity(createUpdateGoodsDeliveryDto);
        doReturn(createdGoodsDelivery).when(goodsDeliveryService).create(goodsDelivery, 1L);

        mockMvc.perform(post("/api/v1/delivery")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @DisplayName("creating should throw MethodArgumentNotValidException")
    void create_should_throw_MethodArgumentNotValidException() throws Exception {
        CreateUpdateGoodsDeliveryDto badRequest = CreateUpdateGoodsDeliveryDto.builder()
                .goodsId(1L)
                .amount(0)
                .name("BrainDance")
                .build();
        String badJson = objectMapper.writeValueAsString(badRequest);

        mockMvc.perform(post("/api/v1/delivery")
                        .contentType(APPLICATION_JSON)
                        .content(badJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }
}
