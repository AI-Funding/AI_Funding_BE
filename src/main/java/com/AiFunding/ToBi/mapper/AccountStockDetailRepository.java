package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStockDetailRepository extends JpaRepository<AccountStockDetailEntity, Long> {
    AccountStockDetailEntity findByStockAndId(StockEntity stockEntity, Long id);
}
