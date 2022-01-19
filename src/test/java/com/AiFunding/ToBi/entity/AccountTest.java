package com.AiFunding.ToBi.entity;


import com.AiFunding.ToBi.mapper.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void save(){
        Account account = Account.builder()
                .accountType("3")
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
        assertThat(account.get().getYield()).isEqualTo(109421);
    }

    @Test
    public void update(){
        Optional<Account> account = accountRepository.findById(3L);

        accountRepository.save(Account.builder().accountNumber(3L).accountType("4")
                .visiable(account.get().getVisiable())
                .createTime(account.get().getCreateTime())
                .modifiedTime(LocalDateTime.now())
                .aiType(account.get().getAiType())
                .balance(account.get().getBalance())
                .yield(9278421)
                .build());

        assertThat(accountRepository.findById(3L).get().getAccountType()).isEqualTo("4");
        assertThat(accountRepository.findById(3L).get().getYield()).isEqualTo(9278421);
    }

    @Test
    public void deleteAll(){
        accountRepository.deleteAll();
    }

}
