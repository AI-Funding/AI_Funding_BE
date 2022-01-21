package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "ACCOUNT") // ACCOUNT이라는 이름의 테이블을 매핑해줍니다.
public class AccountEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sequence")
    private CustomerInformationEntity customerInformation;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column
    private Long balance;

    @Column
    private Integer yield;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    @Column(name = "ai_type")
    private Integer aiType;

    @Column(name = "account_type")
    private String accountType;

    @Column
    private Boolean visiable;

    @OneToMany(mappedBy = "accountEntity")
    List<AccountDetailEntity> accountDetails = new ArrayList<>();

    @OneToMany(mappedBy = "accountEntity")
    List<AccountStockDetailEntity> accountStockDetails = new ArrayList<>();

    @OneToMany(mappedBy = "accountEntity")
    List<TransactionDetailEntity> transactionDetails = new ArrayList<>();

}
