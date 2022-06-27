package com.AiFunding.ToBi.dto.profit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AccountProfitResponseDto {
    //투자 AI 이름
    String aiName;
    //계좌 이름
    String accountName;
    //총 평가 금액
    Long todayTotalBalance;
    //총 손익금 (퍼센트)
    Double totalProfitPersent;
    //총 손익금 (원₩)
    Long totalProfitWon;
    //하루 손익금 (원₩)
    Long todayProfitWon;
    //하루 수익률 (퍼센트)
    Double todayProfitPersent;
    //수익률 세부정보 리스트
    List<ProfitDetailResponseDto> profitDetails;

    public AccountProfitResponseDto(
            final String aiName,
            final String accountName,
            final Long todayTotalBalance,
            final Double totalProfitPersent,
            final Long totalProfitWon,
            final Long todayProfitWon,
            final Double todayProfitPersent,
            final List<ProfitDetailResponseDto> profitDetails) {
        this.aiName = aiName;
        this.accountName = accountName;
        this.todayTotalBalance = todayTotalBalance;
        this.totalProfitPersent = totalProfitPersent;
        this.totalProfitWon = totalProfitWon;
        this.todayProfitWon = todayProfitWon;
        this.todayProfitPersent = todayProfitPersent;
        this.profitDetails = profitDetails;
    }
}
