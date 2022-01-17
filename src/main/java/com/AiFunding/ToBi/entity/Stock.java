package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "STOCK")
public class Stock {

    @Id
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "end_price")
    private Integer endPrice;

    @Column(name = "now_price")
    private Integer nowPrice;
}
