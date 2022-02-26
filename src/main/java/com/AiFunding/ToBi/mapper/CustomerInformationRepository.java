package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInformationRepository extends JpaRepository<CustomerInformationEntity,Long> {
    // user_sequence와 login_type을 통한 유저 정보 찾기
//    @Query(value = "SELECT * FROM CUST_INFO CUST_INFO WHERE user_sequence=?0 AND login_type=?1", nativeQuery = true)
    CustomerInformationEntity findByIdAndLoginType(Long id, String loginType);
}
