package com.AiFunding.ToBi;

import com.AiFunding.ToBi.dto.Tab.History.HistoryListResponseDto;
import com.AiFunding.ToBi.dto.Tab.History.HistoryResponseDto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryDtoTest {

    @Test
    public void lombokTest(){
        String stockName = "삼성전자";
        LocalDateTime tradeDate = LocalDateTime.now();
        Long totalPrice = Long.valueOf(10000000);
        String tradeType = "국내";
        Integer tradeAmount = 2;
        Long currentPrice = Long.valueOf(50000);
        Long tradePrice = Long.valueOf(49000);

        HistoryListResponseDto listDto = new HistoryListResponseDto(stockName, tradeDate, totalPrice, tradeType, tradeAmount, currentPrice, tradePrice);

        List<HistoryListResponseDto> tradeHistory = new ArrayList<HistoryListResponseDto>(Arrays.asList(listDto));

        HistoryResponseDto Dto = new HistoryResponseDto(tradeHistory);

        assertThat(listDto.getStockName()).isEqualTo(stockName);
        assertThat(listDto.getTradeDate()).isEqualTo(tradeDate);
        assertThat(listDto.getTotalPrice()).isEqualTo(totalPrice);
        assertThat(listDto.getTradeType()).isEqualTo(tradeType);
        assertThat(listDto.getTradeAmount()).isEqualTo(tradeAmount);
        assertThat(listDto.getCurrentPrice()).isEqualTo(currentPrice);
        assertThat(listDto.getTradePrice()).isEqualTo(tradePrice);
        assertThat(Dto.getTradeHistory()).isEqualTo(tradeHistory);
    }

}
