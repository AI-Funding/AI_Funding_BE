package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "STOCK") // STOCK이라는 이름의 테이블을 매핑해줍니다.
public class StockEntity implements Serializable {

    @Id // PK를 설정합니다.
    @Column(name = "item_id") // name이라는 속성에 맞게 Column을 매핑합니다.
    private Integer itemId;

    @Column(name = "item_name") // Column을 매핑합니다.
    private String itemName;

    @Column(name = "end_price") // Column을 매핑합니다.
    private Integer endPrice;

    @Column(name = "now_price") // Column을 매핑합니다.
    private Integer nowPrice;
}
