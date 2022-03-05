package com.AiFunding.ToBi.dto.trade.signal;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TradeSignalRequestDto {

    private List<TradeSignalDetailDto> stockInfo;

    TradeSignalRequestDto(final List<TradeSignalDetailDto> stockInfo){
        this.stockInfo = stockInfo;
    }
}
