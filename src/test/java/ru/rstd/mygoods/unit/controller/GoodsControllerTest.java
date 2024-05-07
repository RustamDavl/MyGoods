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
import ru.rstd.mygoods.dto.goods.CreateUpdateGoodsDto;
import ru.rstd.mygoods.dto.goods.ReadGoodsDto;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.exception.GoodsNotFoundException;
import ru.rstd.mygoods.mapper.GoodsMapper;
import ru.rstd.mygoods.mapper.GoodsMapperImpl;
import ru.rstd.mygoods.service.GoodsService;
import ru.rstd.mygoods.service.impl.GoodsServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GoodsControllerTest {

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private GoodsMapper goodsMapper;
    private GoodsService goodsService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        goodsService = Mockito.mock(GoodsServiceImpl.class);
        goodsMapper = Mockito.mock(GoodsMapperImpl.class);
        Mockito.mock(ControllerAdvice.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new GoodsController(goodsService, goodsMapper))
                .setControllerAdvice(new ControllerAdvice())
                .build();
    }

    @Test
    void getById_should_pass() throws Exception {
        ReadGoodsDto readGoodsDto = ReadGoodsDto.builder()
                .id(1L)
                .name("BrainDance")
                .description("Everything on full blast.")
                .price("10000")
                .inStock(true)
                .build();
        Goods goods = Goods.builder()
                .id(1L)
                .name("BrainDance")
                .description("Everything on full blast.")
                .price(BigDecimal.valueOf(10000))
                .inStock(true)
                .build();

        doReturn(readGoodsDto).when(goodsMapper).toReadGoodsDto(goods);
        doReturn(goods).when(goodsService).getById(1L);
        mockMvc.perform(get("/api/v1/goods/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("find by id should throw GoodsNotFoundException")
    void getById_should_throw_GoodsNotFoundException() throws Exception {
        doThrow(GoodsNotFoundException.class).when(goodsService).getById(1L);

        mockMvc.perform(get("/api/v1/goods/{id}", 1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof GoodsNotFoundException));
    }

    @Test
    @DisplayName("creating goods should pass")
    void create_should_pass() throws Exception {
        CreateUpdateGoodsDto createUpdateGoodsDto = CreateUpdateGoodsDto.builder()
                .name("BrainDance")
                .description("Everything on full blast.")
                .price("10000")
                .inStock("true")
                .build();

        Goods goods = Goods.builder()
                .name("BrainDance")
                .description("Everything on full blast.")
                .price(BigDecimal.valueOf(10000))
                .inStock(true)
                .build();

        Goods createdGoods = Goods.builder()
                .id(1L)
                .name("BrainDance")
                .description("Everything on full blast.")
                .price(BigDecimal.valueOf(10000))
                .inStock(true)
                .build();
        String json = objectMapper.writeValueAsString(createUpdateGoodsDto);

        doReturn(goods).when(goodsMapper).toEntity(createUpdateGoodsDto);
        doReturn(createdGoods).when(goodsService).create(goods);

        mockMvc.perform(post("/api/v1/goods")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @DisplayName("creating should throw MethodArgumentNotValidException")
    void create_should_throw_MethodArgumentNotValidException() throws Exception {
        CreateUpdateGoodsDto badRequest = CreateUpdateGoodsDto.builder()
                .name("BrainDance")
                .description("Everything on full blast.")
                .price("-1")
                .inStock("true")
                .build();

        String badJson = objectMapper.writeValueAsString(badRequest);

        mockMvc.perform(post("/api/v1/goods")
                        .contentType(APPLICATION_JSON)
                        .content(badJson))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }
}
