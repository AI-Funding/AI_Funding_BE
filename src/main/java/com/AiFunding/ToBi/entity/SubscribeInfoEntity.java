package com.AiFunding.ToBi.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity // Jpa를 사용하기 위한 Entity 설정
@NoArgsConstructor // 파라미터가 없는 생성자 생성
@AllArgsConstructor // 모든 파라미터가 있는 생성자 생성
@Builder // 빌더 패턴 사용
@ToString // ToString 오버라이딩
@Getter // Getter 사용
@Table(name = "SUBSCRIBE_INFO") // SUBSCRIBE_INFO라는 이름의 테이블을 매핑해줍니다.
public class SubscribeInfoEntity implements Serializable {

    @Id // PK를 설정해줍니다.
    @Column(name = "subscribe_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean subscription;

    @Column(name = "subscribe_date")
    @NotNull
    private LocalDateTime subscribeDate;

    @Column(name = "expire_date")
    @NotNull
    private LocalDateTime expireDate;

    //fk
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subscribeInfo")
    private CustomerInformationEntity customer;
}
