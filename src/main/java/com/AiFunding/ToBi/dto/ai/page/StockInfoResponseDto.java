package com.AiFunding.ToBi.dto.ai.page;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StockInfoResponseDto {
    //주식이름
    private String stockName;
    //현재가
    private Long currentPrice;
    //전일종가 대비 현재가
    private Long stockPriceChange;
    //전일종가 대비 변동률
    private Double stockRateChange;
    //상승 하락 변동률 상승==1 / 하락==-1 / 유지==0
    private int stockChange;
    //주식상세 리스트
    private List<StockDetailResponseDto> stockDetails;


    public StockInfoResponseDto(final String stockName,
                                final Long currentPrice,
                                final Long stockPriceChange,
                                final double stockRateChange,
                                final int stockChange,
                                final List<StockDetailResponseDto> stockDetails) {
        this.stockName = stockName;
        this.currentPrice = currentPrice;
        this.stockPriceChange = stockPriceChange;
        this.stockRateChange = stockRateChange;
        this.stockChange = stockChange;
        this.stockDetails = stockDetails;
    }
}
