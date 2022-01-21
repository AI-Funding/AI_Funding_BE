package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.TransactionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetailEntity,Long> {
}
