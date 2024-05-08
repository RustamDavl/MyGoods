package ru.rstd.mygoods.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rstd.mygoods.entity.Goods;
import ru.rstd.mygoods.repository.custom.GoodsFilterRepository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long>, GoodsFilterRepository {

    List<Goods> findAllBy(Sort sort);
}
