package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "TRADING_DETAIL") // TRADING_DETAIL 이름의 테이블을 매핑해줍니다.
public class TradingDetailEntity implements Serializable {

    @Id // PK를 설정해줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT로 DB에 ID생성을 위임합니다.
    @Column(name = "trade_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계로 지연로딩을 사용해서 매핑함
//    @JoinColumn(name = "account_number") // account_number라는 컬럼을 통해 join함
//    private AccountEntity accountEntity;

//    @Column(name = "item_id", unique = true) // unique속성을 통해서 유일한 값으로 설정합니다. Column을 매핑합니다.
//    private String itemId;

    @Column(name = "trading_type", length = 2)
    @NotNull
    private String tradingType;

    @Column(name = "trading_price")
    @NotNull
    private Long tradingPrice;

    @Column(name = "trading_amount")
    @NotNull
    private Integer tradingAmount;

    @CreatedDate
    @Column(name = "create_at")
    @NotNull
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private StockEntity stockEntity;
}
