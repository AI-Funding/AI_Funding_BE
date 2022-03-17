package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.TradeSignalEntity;
import com.AiFunding.ToBi.entity.TradingDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingDetailRepository extends JpaRepository<TradeSignalEntity,Long> {
}
