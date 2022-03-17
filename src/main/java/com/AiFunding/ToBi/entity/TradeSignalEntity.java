package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "TRADE_SIGNAL") // SUBSCRIBE_INFO라는 이름의 테이블을 매핑해줍니다.
public class TradeSignalEntity extends BaseCreateEntity{

    @Id
    @Column(name = "trade_signal_id")
    private Long id;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "trade_signal")
    private Integer tradeSignal;

    @Column(name = "trade_model")
    private String tradeModel;

    @Column(name = "trade_amount")
    private String tradeAmount;
}
