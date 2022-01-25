package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.SubscribeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<SubscribeInfoEntity, Long> {
}
