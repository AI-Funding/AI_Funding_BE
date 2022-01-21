package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInformationRepository extends JpaRepository<CustomerInformationEntity,Long> {
}
