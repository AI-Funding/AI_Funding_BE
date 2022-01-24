package com.AiFunding.ToBi.entity;

import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.mapper.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerEntityTest {

    @Autowired
    private CustomerInformationRepository customRepository;

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void save(){

        List<AccountEntity> accountEntities = new ArrayList<>();

        CustomerInformationEntity custom = CustomerInformationEntity.builder()
                .userId("해찬유").nickname("유해찬").loginType("1").email("haechan@naver.com")

                .build();
        customRepository.save(custom);

        StockEntity stock = StockEntity.builder()
                .id("A1234")
                .itemName("해찬").nowPrice(12312).build();
        stockRepository.save(stock);

        Optional<CustomerInformationEntity> findCustom = customRepository.findById(1L);
        assertThat(findCustom.get().getNickname()).isEqualTo("유해찬");



    }

}
