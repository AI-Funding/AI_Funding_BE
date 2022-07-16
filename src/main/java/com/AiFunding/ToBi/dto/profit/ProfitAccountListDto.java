package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProfitAccountListDto {
    //계좌별 누적수익률 정보 리스트
    private List<ProfitAccountInfoResponseDto> accounts;

    public ProfitAccountListDto(final List<ProfitAccountInfoResponseDto> accounts) {
        this.accounts = accounts;
    }
}