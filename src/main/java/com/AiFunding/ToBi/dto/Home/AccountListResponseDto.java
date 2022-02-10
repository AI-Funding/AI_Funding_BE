package com.AiFunding.ToBi.dto.Home;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class AccountListResponseDto {

    private String nickname;

    private Long balance;

    private LocalDateTime createAt;

    private Double todayIncome;

    private Long todayProfit;

    private Double totalIncome;

    private Long totalProfit;

    private List<StockListResponseDto> stocks;

    public AccountListResponseDto(final String nickname, final Long balance, final LocalDateTime createAt, final Double todayIncome
    , final Long todayProfit, final Double totalIncome, final Long totalProfit, final List<StockListResponseDto> stocks){
        this.nickname = nickname;
        this.balance = balance;
        this.createAt = createAt;
        this.todayIncome = todayIncome;
        this.todayProfit = todayProfit;
        this.totalIncome = totalIncome;
        this.totalProfit = totalProfit;
        this.stocks = stocks;
    }
}
