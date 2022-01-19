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
@Table(name = "ACCOUNT_DETAIL")
public class AccountDetail implements Serializable {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number")
    private Account account;

    @Column(name = "deposit_type")
    private String depositType;

    @Column(name = "deposit_amount")
    private Long depositAmount;

    @Column(name = "create_time")
    private LocalDateTime createTime;

}
