package com.AiFunding.ToBi.entity;

import com.AiFunding.ToBi.mapper.TradingDetailRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TradeTest {

    @Autowired
    private TradingDetailRepository tradingDetailRepository;

    @AfterEach
    public void afterEach(){
        tradingDetailRepository.deleteAll();
    }

    @Test
    public void saveTest(){
        TradeSignalEntity newData = TradeSignalEntity.builder()
                .tradeAmount(3.33)
                .tradeModel("HDL")
                .stockName("삼성전자")
                .tradeSignal(0)
                .build();
        tradingDetailRepository.save(newData);

        Optional<TradeSignalEntity> findData = tradingDetailRepository.findById(1L);
        Assertions.assertThat(newData.getTradeAmount()).isEqualTo(3.33);
        Assertions.assertThat(newData.getTradeModel()).isEqualTo("HDL");
        Assertions.assertThat(newData.getStockName()).isEqualTo("삼성전자");
        Assertions.assertThat(newData.getTradeSignal()).isEqualTo(0);
    }
}
