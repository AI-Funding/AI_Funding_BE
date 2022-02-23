package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomerInformationEntity, Long> {
    Optional<CustomerInformationEntity> findByEmail(String email);//email로 이미 생성된 사용자가 있는지 판별
}
