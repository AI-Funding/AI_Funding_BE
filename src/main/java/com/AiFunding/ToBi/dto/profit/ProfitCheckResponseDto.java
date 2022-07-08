package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProfitCheckResponseDto {
    //계좌개수
    private int accountNumber;
    //계좌리스트AccountProfitResponseDto List
    private List<AccountProfitResponseDto> account;

    public ProfitCheckResponseDto(final List<AccountProfitResponseDto> accountProfitResponseDtoList, final int accountNumber) {
        this.account = accountProfitResponseDtoList;
        this.accountNumber = accountNumber;
    }
}