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
public class AccountDetailEntity implements Serializable {

    @Id // PK를 설정해줍니다.
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일로 account_number와 매핑을 해줍니다. 또한, 타입은 지연로딩을 합니다.
    @JoinColumn(name = "account_number") // account_number로 Join을 합니다.
    private AccountEntity accountEntity;

    @NotNull
    @Column(name = "deposit_type")
    private String depositType;

    @NotNull
    @Column(name = "deposit_amount")
    private Long depositAmount;

    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

}
