package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StockPriceByDayRepository extends JpaRepository<StockPriceByDayEntity, Long> {
    List<StockPriceByDayEntity> findByStockAndCreateAtBetween(StockEntity stock, LocalDateTime start, LocalDateTime end);
}