package com.AiFunding.ToBi.dto.Home;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private Long userSequence;

    private String loginType;

    public UserRequestDto(final Long userSequence, final String loginType){
        this.userSequence = userSequence;
        this.loginType = loginType;
    }
}
