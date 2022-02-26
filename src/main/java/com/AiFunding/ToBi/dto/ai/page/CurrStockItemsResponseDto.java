package com.AiFunding.ToBi.dto.ai.page;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CurrStockItemsResponseDto {
    //현재종목리스트
    private List<AccountInfoResponseDto> accountInfoResponseDtos;

    public CurrStockItemsResponseDto(final List<AccountInfoResponseDto> accountInfoResponseDtos) {
        this.accountInfoResponseDtos = accountInfoResponseDtos;
    }
}
