package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.TradeSignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeSignalRepository extends JpaRepository<TradeSignalEntity, Long> {

    List<TradeSignalEntity> findByCreateAtBetween(LocalDateTime startTime, LocalDateTime endTime);
}
