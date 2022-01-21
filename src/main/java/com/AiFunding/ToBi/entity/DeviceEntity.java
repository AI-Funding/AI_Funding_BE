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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sequence")
    private CustomerInformationEntity customerInformation;

    @Column(name = "device_number", unique = true)
    private String deviceNumber;

    @Column
    private String password;

}
