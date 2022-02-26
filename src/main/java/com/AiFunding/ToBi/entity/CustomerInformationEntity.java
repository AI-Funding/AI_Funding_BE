package com.AiFunding.ToBi.entity;

import com.AiFunding.ToBi.auth.Role;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity // JPA가 사용하도록 Entity라는 어노테이션을 붙입니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 만듭니다.
@AllArgsConstructor // 파라미터가 모두 있는 생성자를 만듭니다.
@Builder // 빌더 패턴을 사용합니다.
@ToString // ToString을 오버라이딩합니다.
@Getter // Getter를 사용합니다.
@Table(name = "CUSTOMER_INFO")  // CUSTOMER_INFO라는 테이블에 매핑을 합니다.

public class CustomerInformationEntity extends BaseCreateModifiedEntity {

    @Id // PK 값을 설정합니다.
    @Column(name = "customer_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터 베이스에게 ID 생성을 AUTO_INCREMENT로 위임합니다.
    private Long id;

    @Column(length = 50)
    @NotNull
    private String nickname;

    @Column(name = "user_id", length = 50, unique = true)
    @NotNull
    private String userId;

    @Column(length = 100)
    @NotNull
    private String email;

    @Column(name = "login_type", length = 2)
    @NotNull
    private String loginType;

    private String password;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<AccountEntity> accounts = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "subscribe_info_id")
    private SubscribeInfoEntity subscribeInfo;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleKey() {
        return this.role.getKey();
    }

    public CustomerInformationEntity update(String name) {
        this.nickname=name;
        return this;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<PostEntity> posts = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<CommentEntity> comments = new ArrayList<>();
}
