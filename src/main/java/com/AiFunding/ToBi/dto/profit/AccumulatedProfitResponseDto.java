package com.AiFunding.ToBi.dto.profit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AccumulatedProfitResponseDto {
    //누적수익률 날짜
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime date;
    //누적수익률
    private Double profit;

    public AccumulatedProfitResponseDto(final LocalDateTime date, final Double profit) {
        this.date = date;
        this.profit = profit;
    }
}