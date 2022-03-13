package com.AiFunding.ToBi.dto.profit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProfitDetailResponseDto {
    //해당일 손익금(퍼센트)
    private Double creatAtProfitPersent;
    //해당일 손익금(원₩)
    private Long creatAtProfitWon;
    //해당 날짜
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime creatAt;

    public ProfitDetailResponseDto(
            final Double creatAtProfitPersent,
            final Long creatAtProfitWon,
            final LocalDateTime creatAt) {
        this.creatAtProfitPersent = creatAtProfitPersent;
        this.creatAtProfitWon = creatAtProfitWon;
        this.creatAt = creatAt;
    }
}
