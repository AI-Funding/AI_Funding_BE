package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.AccountEntity;
import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountStockDetailRepository extends JpaRepository<AccountStockDetailEntity, Long> {
    AccountStockDetailEntity findByStockAndId(StockEntity stockEntity, Long id);
    List<AccountStockDetailEntity> findByCreateAtBetweenAndAccount(LocalDateTime start, LocalDateTime end, AccountEntity account);

}
