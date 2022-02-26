package com.AiFunding.ToBi.dto.Home;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockListResponseDto {

    private String itemName;

    private Integer price;

    private Double profit;

    private Double percent_by_account;

    public StockListResponseDto(String itemName, Integer price, Double profit, Double percent_by_account){
        this.itemName = itemName;
        this.price = price;
        this.profit = profit;
        this.percent_by_account = percent_by_account;
    }

}
