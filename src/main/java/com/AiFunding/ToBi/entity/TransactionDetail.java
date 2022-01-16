package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "transaction_detail")
public class TransactionDetail {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "deposit_type")
    private String depositType;

    @Column(name = "deposit_amount")
    private Long depositAmount;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
}
