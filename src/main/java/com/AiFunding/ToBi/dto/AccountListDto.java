package com.AiFunding.ToBi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class AccountListDto {

    private Long balance;

    private LocalDateTime createAt;

    private Double todayIncome;

    private Integer todayProfit;

    private Double totalIncome;

    private Integer totalProfit;

    private List<StockListDto> stocks;

    public AccountListDto(final Long balance, final LocalDateTime createAt, final Double todayIncome
    , final Integer todayProfit, final Double totalIncome, final Integer totalProfit, final List<StockListDto> stocks){
        this.balance = balance;
        this.createAt = createAt;
        this.todayIncome = todayIncome;
        this.todayProfit = todayProfit;
        this.totalIncome = totalIncome;
        this.totalProfit = totalProfit;
        this.stocks = stocks;
    }
}
