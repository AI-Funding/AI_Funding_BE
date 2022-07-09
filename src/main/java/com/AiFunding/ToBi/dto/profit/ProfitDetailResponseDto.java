package com.AiFunding.ToBi.dto.profit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProfitDetailResponseDto {
    //해당일 손익금(퍼센트)
    private Double createAtProfitPersent;
    //해당일 손익금(원₩)
    private Long createAtProfitWon;
    //해당 날짜
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createAt;

    public ProfitDetailResponseDto(
            final Double createAtProfitPersent,
            final Long createAtProfitWon,
            final LocalDateTime createAt) {
        this.createAtProfitPersent = createAtProfitPersent;
        this.createAtProfitWon = createAtProfitWon;
        this.createAt = createAt;
    }
}
