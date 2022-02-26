package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
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
public class AccountEntity extends BaseCreateModifiedEntity implements Serializable {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, name = "account_number", unique = true)
    private String accountNumber;

    @Column(length = 50)
    private String nickname;

    @Column
    private Long balance;

    @Column(name = "today_total_balance")
    private Long todayTotalBalance;

    @Column(name = "yesterday_total_balance")
    private Long yesterdayTotalBalance;

    @Column(name = "ai_type")
    @NotNull
    private Integer aiType;

    @Column(name = "user_visable")
    @NotNull
    private Boolean userVisiable;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    List<AccountDetailEntity> accountDetails = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    List<AccountStockDetailEntity> accountStocks = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    List<TradingDetailEntity> tradingEntities = new ArrayList<>();

    //FK
    @ManyToOne
    @JoinColumn(name = "customer_info_id")
    private CustomerInformationEntity customer;


}
