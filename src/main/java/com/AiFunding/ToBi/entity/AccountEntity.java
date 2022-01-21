package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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

    @CreatedDate // 자동으로 생성된 날짜가 들어가게 함
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @LastModifiedDate // 자동으로 데이터를 업데이트 할 때 날짜가 들어가게 됩니다.
    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    @Column(name = "ai_type")
    @NotNull
    private Integer aiType;

    @Column(name = "account_type")
    private String accountType;

    @NotNull
    private Boolean visiable;

    @OneToMany(mappedBy = "accountEntity")
    List<AccountDetailEntity> accountDetails = new ArrayList<>();

    @OneToMany(mappedBy = "accountEntity")
    List<AccountStockDetailEntity> accountStockDetails = new ArrayList<>();

    @OneToMany(mappedBy = "accountEntity")
    List<TransactionDetailEntity> transactionDetails = new ArrayList<>();

}
