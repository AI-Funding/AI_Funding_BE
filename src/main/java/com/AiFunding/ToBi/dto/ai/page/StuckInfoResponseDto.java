package com.AiFunding.ToBi.dto.ai.page;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StuckInfoResponseDto {
    //주식이름
    private String stuckName;
    //현재가
    private Long currentPrice;
    //가격변동
    private Long priceFluc;
    //등락변동
    private Double rateFluc;
    //변동사항
    private String fluctuation;

    public StuckInfoResponseDto(final String stuckName,
                                final Long currentPrice,
                                final Long priceFluc,
                                final Double rateFluc,
                                final String fluctuation) {
        this.stuckName = stuckName;
        this.currentPrice = currentPrice;
        this.priceFluc = priceFluc;
        this.rateFluc = rateFluc;
        this.fluctuation = fluctuation;
    }
}
