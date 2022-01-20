package com.AiFunding.ToBi.mapper;

import com.AiFunding.ToBi.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
//    @Query(value = "select * from account where user_name = ?1", nativeQuery = true)
//    Account findAllAccount(String name);
}
