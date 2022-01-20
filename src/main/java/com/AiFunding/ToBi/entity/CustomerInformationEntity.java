package com.AiFunding.ToBi.entity;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "CUST_INFO",
    uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"nickname","user_id"}
        )
    })
public class CustomerInformationEntity {
    @Id
    @Column(name = "user_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


}
