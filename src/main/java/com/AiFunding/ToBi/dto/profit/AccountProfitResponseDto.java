package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AccountProfitResponseDto {
    //투자 AI Type
    private Integer aiType;
    //계좌 이름
    private String accountName;
    //총 평가 금액
    private Long todayTotalBalance;
    //총 손익금 (퍼센트)
    private Double totalProfitPersent;
    //총 손익금 (원₩)
    private Long totalProfitWon;
    //하루 손익금 (원₩)
    private Long todayProfitWon;
    //하루 수익률 (퍼센트)
    private Double todayProfitPersent;
    //수익률 세부정보 리스트
    private List<ProfitDetailResponseDto> profitDetails;

    public AccountProfitResponseDto(
            final Integer aiType,
            final String accountName,
            final Long todayTotalBalance,
            final Double totalProfitPersent,
            final Long totalProfitWon,
            final Long todayProfitWon,
            final Double todayProfitPersent,
            final List<ProfitDetailResponseDto> profitDetails) {
        this.aiType = aiType;
        this.accountName = accountName;
        this.todayTotalBalance = todayTotalBalance;
        this.totalProfitPersent = totalProfitPersent;
        this.totalProfitWon = totalProfitWon;
        this.todayProfitWon = todayProfitWon;
        this.todayProfitPersent = todayProfitPersent;
        this.profitDetails = profitDetails;
    }
}