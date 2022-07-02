package com.AiFunding.ToBi.dto.ai.History;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TradeHistoryListResponseDto {

    private String accountName;
    private List<TradeHistoryResponseDto> tradeHistory;

    public TradeHistoryListResponseDto(final String accountName, final List<TradeHistoryResponseDto> tradeHistory){
        this.accountName = accountName;
        this.tradeHistory = tradeHistory;
    }
}
