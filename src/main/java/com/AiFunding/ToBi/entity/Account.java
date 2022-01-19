package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // Entity 선언
@Table(name = "ACCOUNT") // 사용 테이블 선언
@ToString // toString 오버라이딩
@Getter // Getter 선언
@Builder // 빌더 패턴 선언
@AllArgsConstructor // 모든 인자를 가지는 생성자 선언
@NoArgsConstructor // 기본 생성자 선언
public class Account implements Serializable {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSequence;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
