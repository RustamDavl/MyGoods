package ru.rstd.mygoods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rstd.mygoods.entity.GoodsDelivery;

import java.util.Optional;

@Repository
public interface GoodsDeliveryRepository extends JpaRepository<GoodsDelivery, Long> {
    Optional<GoodsDelivery> findByGoodsId(Long goodsId);
}
