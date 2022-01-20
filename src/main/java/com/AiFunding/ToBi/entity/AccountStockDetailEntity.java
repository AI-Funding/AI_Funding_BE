package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "ACCOUNT_STOCK_DETAIL")
public class AccountStockDetail implements Serializable {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number")
    private Account account;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "stock_amount")
    private Integer stockAmount;

    @Column(name = "average_price")
    private Integer averagePrice;

    @Column(name = "yield")
    private Double yield;


}
