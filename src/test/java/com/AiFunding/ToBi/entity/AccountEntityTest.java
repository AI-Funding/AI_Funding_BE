package com.AiFunding.ToBi.entity;


import com.AiFunding.ToBi.mapper.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void save(){
        // insert into account value()
        Account account = Account.builder()
                .accountType("6")
                .visiable(true)
                .createTime(LocalDateTime.now())
                .modifiedTime(LocalDateTime.now())
                .aiType(5)
                .balance(10000L)
                .yield(109421)
                        .build();

        accountRepository.save(account);

        Account findByDatabase = accountRepository.findById(3L).get();
        assertThat(findByDatabase).usingRecursiveComparison().isEqualTo(account);
    }

    @Test
    public void read(){
        Optional<Account> account = accountRepository.findById(1L);
        assertThat(account.get().getYield()).isEqualTo(23);
    }

    @Test
    public void update(){
        Optional<Account> account = accountRepository.findById(1L);

        accountRepository.save(Account.builder().accountNumber(1L).accountType("4")
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
        accountRepository.deleteAll();
    }

}
