package com.AiFunding.ToBi.dto.Home;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    @JsonProperty("customer_info_id")
    private Long id;

    @JsonProperty("login_type")
    private String loginType;

    public UserRequestDto(final Long id, final String loginType){
        this.id = id;
        this.loginType = loginType;
    }
}
