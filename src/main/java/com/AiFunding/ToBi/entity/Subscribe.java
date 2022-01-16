package com.AiFunding.ToBi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "subscribe")
public class Subscribe {

    @Id
    @Column(name = "user_sequence")
    private Long userSequence;

    @Column
    private Boolean subscription;

    @Column(name = "subscribe_date")
    private LocalDateTime subscribeDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    // TODO: Cost_info Entity 작성이후 one To one 구현할 것
}
