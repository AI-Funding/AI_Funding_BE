package com.AiFunding.ToBi.entity;

import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerEntityTest {

    @Autowired
    private CustomerInformationRepository customRepository;

    @Test
    public void save(){
        // insert into cust_info (birth, createTime, email, loginType, nickname, phoneNumber, userId)
        // values(?, ?, ?, ?, ?, ?, ?)
        CustomerInformationEntity custom = CustomerInformationEntity.builder()
                .birth(LocalDate.of(1999,03,16))
                .createTime(LocalDateTime.now())
                .email("haechan@naver.com")
                .loginType("1")
                .nickname("해찬유")
                .phoneNumber("010-1111-2222")
                .userId("haechan").build();
        customRepository.save(custom);

        // select * FROM cust_info where user_sequence=1;
        CustomerInformationEntity findId = customRepository.findById(1L).get();
        assertThat(findId).usingRecursiveComparison().isEqualTo(custom);
    }

}
