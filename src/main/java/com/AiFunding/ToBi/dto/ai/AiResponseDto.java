package com.AiFunding.ToBi.dto.ai;

import com.AiFunding.ToBi.dto.ai.History.AccountTradeHistoryResponseDto;
import com.AiFunding.ToBi.dto.ai.page.CurrStockItemsResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AiResponseDto {
    private CurrStockItemsResponseDto currStockItemsResponseDto;
    private AccountTradeHistoryResponseDto accountTradeHistoryResponseDto;

    public AiResponseDto(final CurrStockItemsResponseDto currStockItemsResponseDto, final AccountTradeHistoryResponseDto accountTradeHistoryResponseDto) {
        this.currStockItemsResponseDto = currStockItemsResponseDto;
        this.accountTradeHistoryResponseDto = accountTradeHistoryResponseDto;
    }
}
