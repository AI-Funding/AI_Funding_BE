package com.AiFunding.ToBi.dto.ai.page;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class StockDetailResponseDto {
    //현재 종가 즉 전일 종가를 넘겨줌
    private int endPrice;
    //당일 날짜
    private LocalDateTime createAt;


    public StockDetailResponseDto(int endPrice, LocalDateTime createAt) {
        this.endPrice = endPrice;
        this.createAt = createAt;
    }

}
