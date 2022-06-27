package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProfitCheckResponseDto {
    //계좌개수
    private Long accountCount;
    //계좌리스트AccountProfitResponseDto List
    private List<AccountProfitResponseDto> accountProfitResponseDtoList;

    public ProfitCheckResponseDto(final List<AccountProfitResponseDto> accountProfitResponseDtoList,
                                  final Long accountCount) {
        this.accountProfitResponseDtoList = accountProfitResponseDtoList;
        this.accountCount = accountCount;
    }
}
