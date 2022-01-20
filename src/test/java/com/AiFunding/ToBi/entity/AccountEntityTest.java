package com.AiFunding.ToBi.entity;


import com.AiFunding.ToBi.mapper.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class AccountEntityTest {

    @Autowired // AccountRepository 에 대해서 빈을 주입합니다.
    private AccountRepository accountRepository;

    @Test
    public void save(){
        // insert into account (account_type, visiable, create_time, modified_time, ai_type, balance, yield)
        // values(?,?,?,?,?,?,?,)
        AccountEntity accountEntity = AccountEntity.builder()
                .accountType("6")
                .visiable(true)
                .createTime(LocalDateTime.now())
                .modifiedTime(LocalDateTime.now())
                .aiType(5)
                .balance(10000L)
                .yield(109421)
                        .build();

        accountRepository.save(accountEntity);

        // SELECT * FROM account WHERE account_number = 3
        AccountEntity findByDatabase = accountRepository.findById(3L).get();
        assertThat(findByDatabase).usingRecursiveComparison().isEqualTo(accountEntity);
    }

    @Test
    public void read(){
        // SELECT * FROM account WHERE account_number=1
        Optional<AccountEntity> account = accountRepository.findById(1L);
        assertThat(account.get().getYield()).isEqualTo(23);
    }

    @Test
    public void update(){

        // SELECT * FROM account WHERE account_number=1
        Optional<AccountEntity> account = accountRepository.findById(1L);

        // insert into account (visiable, create_time, modified_time, ai_type, balance, yield)
        // values(?,?,?,?,?,?)
        accountRepository.save(AccountEntity.builder().accountNumber(1L).accountType("4")
                .visiable(account.get().getVisiable())
                .createTime(account.get().getCreateTime())
                .modifiedTime(LocalDateTime.now())
                .aiType(account.get().getAiType())
                .balance(account.get().getBalance())
                .yield(9278421)
                .build());

        assertThat(accountRepository.findById(1L).get().getAccountType()).isEqualTo("4");
        assertThat(accountRepository.findById(1L).get().getYield()).isEqualTo(9278421);
    }

    @Test
    public void deleteAll(){
        //DELETE FROM account
        accountRepository.deleteAll();
    }

}
