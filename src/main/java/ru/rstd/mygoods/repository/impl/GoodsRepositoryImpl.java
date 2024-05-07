package ru.rstd.mygoods.repository.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.entity.Table;
import ru.rstd.mygoods.repository.GoodsRepository;


import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GoodsRepositoryImpl implements GoodsRepository {

    private final Table<Goods> table;

    @Override
    public Optional<Goods> findById(Long id) {
        return Optional.ofNullable(table.selectById(id));
    }

    @Override
    public List<Goods> findAll() {
        return table.selectAll();
    }

    @Override
    public Goods create(Goods goods) {
        Long id = table.insert(goods);
        return table.selectById(id);
    }

    @Override
    public Goods update(Goods goods) {
        return table.update(goods);
    }

    @Override
    public void delete(Long id) {
        table.delete(id);
    }
}
