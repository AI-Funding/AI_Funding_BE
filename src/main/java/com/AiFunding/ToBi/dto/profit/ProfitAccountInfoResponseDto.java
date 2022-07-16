package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProfitAccountInfoResponseDto {
    //ai번호
    private Integer aiType;
    //계좌이름
    private String nickname;
    //계좌번호
    private String accountNumber;
    //일별 누적수익률 리스트
    private List<AccumulatedProfitResponseDto> profits;

    public ProfitAccountInfoResponseDto(
            final Integer aiType,
            final String nickname,
            final String accountNumber,
            final List<AccumulatedProfitResponseDto> profits){
        this.aiType = aiType;
        this.nickname = nickname;
        this.accountNumber = accountNumber;
        this.profits = profits;
    }

}