package com.AiFunding.ToBi.dto.ai.History;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AccountTradeHistoryResponseDto {
    private List<TradeHistoryListResponseDto> tradeHistoryListResponseDtos;

    public AccountTradeHistoryResponseDto(final List<TradeHistoryListResponseDto> tradeHistoryListResponseDtos) {
        this.tradeHistoryListResponseDtos = tradeHistoryListResponseDtos;
    }
}
