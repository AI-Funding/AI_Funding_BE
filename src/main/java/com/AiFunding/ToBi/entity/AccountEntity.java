package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
public class AccountEntity implements Serializable {

    @Id
    @Column(length = 30, name = "account_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column
    private Long balance;

    @Column
    private Integer income;

    @Column(name = "yesterday_income")
    private Integer yesterdayIncome;

    @CreatedDate // 자동으로 생성된 날짜가 들어가게 함
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @LastModifiedDate // 자동으로 데이터를 업데이트 할 때 날짜가 들어가게 됩니다.
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "ai_type")
    @NotNull
    private Integer aiType;

    @Column(name = "user_visable")
    @NotNull
    private Boolean userVisiable;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_detail_id")
    List<AccountDetailEntity> accountDetails = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_stock_id")
    List<AccountStockDetailEntity> accountStocks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_id")
    List<TradingDetailEntity> tradingEntities = new ArrayList<>();

    //FK
    @ManyToOne
    @JoinColumn(name = "user_sequence")
    private CustomerInformationEntity customer;


}
