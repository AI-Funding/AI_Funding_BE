package com.AiFunding.ToBi.entity;

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
@Table(name = "STOCK_PRICE_BY_DAY") // STOCK_PRICE_BY_DAY이라는 이름의 테이블을 매핑해줍니다.
public class StockPriceByDayEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_price_by_day_id")
    private Long id;

    @Column(name = "end_price")
    private Integer endPrice;

    @Column(name = "start_price")
    private Integer startPrice;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockEntity stock;

}
