package ru.rstd.mygoods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rstd.mygoods.entity.Goods;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
