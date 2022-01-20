package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "TRANSACTION_DETAIL")
public class TransactionDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number")
    private Account account;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "deposit_type")
    private String depositType;

    @Column(name = "deposit_amount")
    private Long depositAmount;

    @Column(name = "create_time")
    private LocalDateTime createTime;


}
