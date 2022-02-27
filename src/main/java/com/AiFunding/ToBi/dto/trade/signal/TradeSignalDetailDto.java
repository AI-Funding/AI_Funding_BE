package com.AiFunding.ToBi.dto.trade.signal;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TradeSignalDetailDto {

    private String stockName;

    private Integer tradeSignal;

    private Integer tradeAmount;

    TradeSignalDetailDto(final String stockName, final Integer tradeSignal, final Integer tradeAmount){
        this.stockName = stockName;
        this.tradeAmount = tradeAmount;
        this.tradeSignal = tradeSignal;
    }
}
