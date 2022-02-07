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
@Table(name = "ACCOUNT_DETAIL") // ACCOUNT_DETAIL이라는 이름의 테이블을 매핑해줍니다.
public class AccountDetailEntity extends BaseCreateEntity implements Serializable{

    @Id // PK를 설정해줍니다.
    @Column(name = "account_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @NotNull
    @Column(name = "deposit_type", length = 2)
    private String depositType;

    @NotNull
    @Column(name = "deposit_amount")
    private Long depositAmount;


}
