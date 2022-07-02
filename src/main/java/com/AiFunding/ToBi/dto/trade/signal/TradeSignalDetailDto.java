package com.AiFunding.ToBi.dto.trade.signal;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TradeSignalDetailDto {

    private String stockName;

    private Integer tradeSignal;

    private Double tradeAmount;

    private String tradeModel;

    TradeSignalDetailDto(final String stockName, final Integer tradeSignal, final Double tradeAmount, final String tradeModel){
        this.stockName = stockName;
        this.tradeAmount = tradeAmount;
        this.tradeSignal = tradeSignal;
        this.tradeModel = tradeModel;
    }
}
