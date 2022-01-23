package com.AiFunding.ToBi.entity;

import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
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

        CustomerInformationEntity custom = CustomerInformationEntity.builder()
                .userId("해찬유").nickname("유해찬").loginType("1").email("haechan@naver.com")
                .build();
        customRepository.save(custom);

        Optional<CustomerInformationEntity> findCustom = customRepository.findById(1L);
        assertThat(findCustom.get().getNickname()).isEqualTo("유해찬");
    }

}
