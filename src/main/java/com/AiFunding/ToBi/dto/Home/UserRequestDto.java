package com.AiFunding.ToBi.dto.Home;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    @JsonProperty("user_sequence")
    private Long userSequence;

    @JsonProperty("login_type")
    private String loginType;

    public UserRequestDto(final Long userSequence, final String loginType){
        this.userSequence = userSequence;
        this.loginType = loginType;
    }
}
