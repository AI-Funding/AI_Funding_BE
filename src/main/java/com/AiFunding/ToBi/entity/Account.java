package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity // Entity 선언
@Table(name = "ACCOUNT") // 사용 테이블 선언
@ToString // toString 오버라이딩
@Getter // Getter 선언
@Builder // 빌더 패턴 선언
@AllArgsConstructor // 모든 인자를 가지는 생성자 선언
@NoArgsConstructor // 기본 생성자 선언
public class Account {

    @Id
    @Column(name = "user_sequence")
    private Long userSequence;

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column
    private Long balance;

    @Column
    private Integer yield;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    @Column(name = "ai_type")
    private Integer aiType;

    @Column(name = "account_type")
    private String accountType;

    @Column
    private Boolean visiable;

}
