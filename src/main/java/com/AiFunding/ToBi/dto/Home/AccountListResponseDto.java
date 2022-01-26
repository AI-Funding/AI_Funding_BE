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

    private Integer todayProfit;

    private Double totalIncome;

    private Integer totalProfit;

    private List<StockListResponseDto> stocks;

    public AccountListResponseDto(final String nickname, final Long balance, final LocalDateTime createAt, final Double todayIncome
    , final Integer todayProfit, final Double totalIncome, final Integer totalProfit, final List<StockListResponseDto> stocks){
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
