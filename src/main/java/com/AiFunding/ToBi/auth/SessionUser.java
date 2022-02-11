package com.AiFunding.ToBi.auth;

import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {
    private  String name;
    private String email;

    public SessionUser(CustomerInformationEntity customerInformationEntity) {
        this.name = customerInformationEntity.getNickname();
        this.email = customerInformationEntity.getEmail();
    }
}
