package com.AiFunding.ToBi.dto.Tab.History;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HistoryListResponseDto {

    //종목 이름
    private String stockName;

    //거래 날짜
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime tradeDate;

    //거래 금액
    private Long totalPrice;

    //거래 종류
    private String tradeType;

    //거래 수량
    private Integer tradeAmount;

    //단가
    private Long currentPrice;

    //매수/도 가
    private Long tradePrice;

    public HistoryListResponseDto(final String stockName, final LocalDateTime tradeDate, final Long totalPrice,
                                  final String tradeType, final Integer tradeAmount, final Long currentPrice, final Long tradePrice){
        this.stockName = stockName;
        this.tradeDate = tradeDate;
        this.totalPrice = totalPrice;
        this.tradeType = tradeType;
        this.tradeAmount = tradeAmount;
        this.currentPrice = currentPrice;
        this.tradePrice = tradePrice;
    }
}
