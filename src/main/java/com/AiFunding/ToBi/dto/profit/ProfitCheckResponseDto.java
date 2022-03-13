package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProfitCheckResponseDto {
    //AccountProfitResponseDto List
    private List<AccountProfitResponseDto> accountProfitResponseDtoList;

    public ProfitCheckResponseDto(final List<AccountProfitResponseDto> accountProfitResponseDtoList) {
        this.accountProfitResponseDtoList = accountProfitResponseDtoList;
    }
}
