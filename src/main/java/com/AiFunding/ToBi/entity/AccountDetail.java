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
@Table(name = "account_detail")
public class AccountDetail {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

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
