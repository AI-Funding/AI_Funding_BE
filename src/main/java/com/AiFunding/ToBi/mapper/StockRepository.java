package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
}
