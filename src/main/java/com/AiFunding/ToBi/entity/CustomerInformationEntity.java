package com.AiFunding.ToBi.entity;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 사용하도록 Entity라는 어노테이션을 붙입니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 만듭니다.
@AllArgsConstructor // 파라미터가 모두 있는 생성자를 만듭니다.
@Builder // 빌더 패턴을 사용합니다.
@ToString // ToString을 오버라이딩합니다.
@Getter // Getter를 사용합니다.
@Table(name = "CUST_INFO", // CUST_INFO라는 테이블에 매핑을 하고 nickname과 user_id라는 컬럼은 unique로 설정을합니다.(2개이상일 떄는 이렇게 해아합니다,)
    uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"nickname","user_id"}
        )
    })
public class CustomerInformationEntity {

    @Id // PK 값을 설정합니다.
    @Column(name = "user_sequence") // Column 이름을 설정합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터 베이스에게 ID 생성을 AUTO_INCREMENT로 위임합니다.
    private Long userSequence;

    @Column
    private String nickname;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column
    private LocalDate birth;

    @Column
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "login_type")
    private String loginType;

    @OneToMany(mappedBy = "customerInformation")
    List<AccountEntity> accounts = new ArrayList<>();

    @OneToOne(mappedBy = "customerInformation")
    private SubscribeEntity subscribeEntities;

    @OneToOne(mappedBy = "customerInformation")
    private DeviceEntity deviceEntity;
}
