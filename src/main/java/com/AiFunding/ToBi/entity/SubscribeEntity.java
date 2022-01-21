package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;

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
@Table(name = "SUBSCRIBE") // SUBSCRIBE라는 이름의 테이블을 매핑해줍니다.
public class SubscribeEntity implements Serializable {

    @Id // PK를 설정해줍니다.
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 지연로딩을 사용하고 일대일 관계를 매핑합니다.
    @JoinColumn(name = "user_sequence") // Join을 사용하기 위한 컬럼은 user_sequence 입니다.
    private CustomerInformationEntity customerInformation;

    @NotNull
    @Column
    private Boolean subscription;

    @NotNull
    @Column(name = "subscribe_date")
    private LocalDateTime subscribeDate;

    @NotNull
    @Column(name = "expire_date")
    private LocalDate expireDate;

}
