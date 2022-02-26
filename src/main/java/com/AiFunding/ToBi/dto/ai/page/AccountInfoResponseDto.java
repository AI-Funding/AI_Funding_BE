package com.AiFunding.ToBi.dto.ai.page;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AccountInfoResponseDto {
    //계좌이름
    private String accountName;
    //주식리스트
    private List<StockInfoResponseDto> stocks;

    public AccountInfoResponseDto(final String accountName, final List<StockInfoResponseDto> stocks) {
        this.accountName = accountName;
        this.stocks = stocks;
    }
}
