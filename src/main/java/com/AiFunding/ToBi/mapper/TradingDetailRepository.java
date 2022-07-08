package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.TradingDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradingDetailRepository extends JpaRepository<TradingDetailEntity,Long> {

    List<TradingDetailEntity> findByCreateAtBetweenAndAccount(LocalDateTime startTime, LocalDateTime endTime, AccountEntity account);
}
