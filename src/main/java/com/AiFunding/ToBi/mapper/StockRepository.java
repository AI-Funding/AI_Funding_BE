package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity,String> {
}
