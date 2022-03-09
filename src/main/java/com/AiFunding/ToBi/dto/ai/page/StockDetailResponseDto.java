package com.AiFunding.ToBi.dto.ai.page;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class StockDetailResponseDto {
    //현재 종가 즉 전일 종가를 넘겨줌
    private int createAtPrice;
    //당일 날짜
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createAt;


    public StockDetailResponseDto(final int createAtPrice,final LocalDateTime createAt) {
        this.createAtPrice = createAtPrice;
        this.createAt = createAt;
    }

}
