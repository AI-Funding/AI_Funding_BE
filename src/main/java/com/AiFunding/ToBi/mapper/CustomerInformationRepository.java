package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerInformationRepository extends JpaRepository<CustomerInformationEntity,Long> {
    // user_sequence와 login_type을 통한 유저 정보 찾기
//    @Query(value = "SELECT * FROM CUST_INFO CUST_INFO WHERE user_sequence=?0 AND login_type=?1", nativeQuery = true)
    Optional<CustomerInformationEntity> findByIdAndLoginType(Long id, String loginType);

    Optional<CustomerInformationEntity> findByIdAndLoginType(String id, String loginType);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByIdAndLoginType(String id, String loginType);
    boolean existsByIdAndLoginType(Long id, String loginType);
}