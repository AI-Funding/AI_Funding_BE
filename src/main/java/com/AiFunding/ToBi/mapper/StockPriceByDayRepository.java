package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.StockPriceByDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceByDayRepository extends JpaRepository<StockPriceByDayEntity,Long> {
}
