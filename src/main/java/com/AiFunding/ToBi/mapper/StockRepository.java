package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity,Long> {

    Optional<StockEntity> findByItemName(String stockName);

}
