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

    @NotNull
    @Column(name = "stock_amount")
    private Integer stockAmount;

    @NotNull
    @Column(name = "average_price")
    private Integer averagePrice;

    @NotNull
    @Column(name = "income")
    private Integer income;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;

    //fk
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockEntity stock;
}
