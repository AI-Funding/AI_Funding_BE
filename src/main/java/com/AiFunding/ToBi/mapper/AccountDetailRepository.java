package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.AccountDetailEntity;
import com.AiFunding.ToBi.entity.AccountStockDetailEntity;
import com.AiFunding.ToBi.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailRepository extends JpaRepository<AccountDetailEntity,Long> {

}
