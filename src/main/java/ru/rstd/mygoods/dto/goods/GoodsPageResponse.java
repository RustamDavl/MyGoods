package ru.rstd.mygoods.dto.goods;

import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

@Value
public class GoodsPageResponse<T> {

    List<T> content;
    Metadata metadata;

    public static <T> GoodsPageResponse<T> of(Page<T> page) {
        Metadata mt = new Metadata(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
        return new GoodsPageResponse<>(page.getContent(), mt);
    }

    @Value
    public static class Metadata {
        int page;
        int size;
        long totalElements;
        int totalPages;
    }
}
