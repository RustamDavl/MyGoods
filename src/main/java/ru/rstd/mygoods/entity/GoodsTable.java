package ru.rstd.mygoods.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Data
@Component
public class GoodsTable implements Table<Goods> {

    private Long sequence = 0L;

    private final HashMap<Long, Goods> goodsTable = new HashMap<>();

    @Override
    public Goods selectById(Long id) {
        return goodsTable.get(id);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsTable.values()
                .stream()
                .sorted(Comparator.comparing(Goods::getId))
                .toList();
    }

    @Override
    public Long insert(Goods goods) {
        sequence += 1;
        goods.setId(sequence);
        goodsTable.put(sequence, goods);
        return sequence;
    }

    @Override
    public Goods update(Goods entity) {
        goodsTable.put(entity.getId(), entity);
        return goodsTable.get(entity.getId());
    }

    @Override
    public void delete(Long id) {
        goodsTable.remove(id);
    }
}
