package com.AiFunding.ToBi.dto.ai.History;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HistoryResponseDto {

    private List<HistoryListResponseDto> tradeHistory;

    public HistoryResponseDto(final List<HistoryListResponseDto> tradeHistory){
        this.tradeHistory = tradeHistory;
    }
}
