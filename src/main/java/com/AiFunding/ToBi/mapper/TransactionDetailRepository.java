package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail,Long> {
}
