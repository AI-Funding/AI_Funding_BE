package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeatmapStockListResponseDto {
    //주식이름
    private String stockName;
    //주식비중
    private Double stockPercent;

    public HeatmapStockListResponseDto(final String stockName, final Double stockPercent) {
        this.stockName = stockName;
        this.stockPercent = stockPercent;
    }
}
