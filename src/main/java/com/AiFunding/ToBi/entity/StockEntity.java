package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "STOCK") // STOCK이라는 이름의 테이블을 매핑해줍니다.
public class StockEntity implements Serializable {

    @Id
    @Column(name = "item_id", length = 20)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "item_name", length = 50)
    @NotNull
    private String itemName;

    @Column(name = "now_price")
    @NotNull
    private Integer nowPrice;

    @OneToMany(mappedBy = "stockEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<StockPriceByDayEntity> stockPriceByDayEntities = new ArrayList<>();

    @OneToMany(mappedBy = "stockEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<TradingDetailEntity> tradingDetailEntities = new ArrayList<>();

}
