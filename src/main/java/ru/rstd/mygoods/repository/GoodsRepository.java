package ru.rstd.mygoods.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.rstd.mygoods.entity.Goods;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long>,
        QuerydslPredicateExecutor<Goods> {

    List<Goods> findAllBy(Sort sort);

    Page<Goods> findAll(Predicate predicate, Pageable pageable);
}
