package ru.rstd.mygoods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rstd.mygoods.entity.GoodsDelivery;
import ru.rstd.mygoods.entity.GoodsSale;

import java.util.Optional;

@Repository
public interface GoodsSaleRepository extends JpaRepository<GoodsSale, Long> {
    Optional<GoodsSale> findByGoodsId(Long goodsId);
}
