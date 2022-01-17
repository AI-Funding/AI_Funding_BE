package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "ACCOUNT_STOCK_DETAIL")
public class AccountStockDetail {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "stock_amount")
    private Integer stockAmount;

    @Column(name = "average_price")
    private Integer averagePrice;

    @Column(name = "yield")
    private Double yield;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

}
