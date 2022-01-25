package com.AiFunding.ToBi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockListDto {

    private String itemName;

    private Integer price;

    private Double profit;

    public StockListDto(String itemName, Integer price, Double profit){
        this.itemName = itemName;
        this.price = price;
        this.profit = profit;
    }

}
