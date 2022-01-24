package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "DEVICE")
public class DeviceEntity {

    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_number", unique = true, length = 50)
    private String deviceNumber;

    @Column(length = 10)
    private String password;

    //fk
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_sequence")
    private CustomerInformationEntity customer;

}
