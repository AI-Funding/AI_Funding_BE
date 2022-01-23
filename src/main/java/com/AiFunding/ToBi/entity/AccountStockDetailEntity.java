package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "ACCOUNT_STOCK_DETAIL") // ACCOUNT_STOCK_DETAIL이라는 이름의 테이블을 매핑해줍니다.
public class AccountStockDetailEntity implements Serializable {

    @Id
    @Column(name = "account_stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number")
    private AccountEntity accountEntity;

    @NotNull
    @Column(name = "item_id", length = 20)
    private String itemId;

    @NotNull
    @Column(name = "stock_amount")
    private Integer stockAmount;

    @NotNull
    @Column(name = "average_price")
    private Integer averagePrice;

    @NotNull
    @Column(name = "income")
    private Double income;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;


}
