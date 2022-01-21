package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "ACCOUNT_STOCK_DETAIL") // ACCOUNT_STOCK_DETAIL이라는 이름의 테이블을 매핑해줍니다.
public class AccountStockDetailEntity implements Serializable {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number")
    private AccountEntity accountEntity;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "stock_amount")
    private Integer stockAmount;

    @Column(name = "average_price")
    private Integer averagePrice;

    @Column(name = "yield")
    private Double yield;


}
