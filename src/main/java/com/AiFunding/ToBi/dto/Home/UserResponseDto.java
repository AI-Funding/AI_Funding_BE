package com.AiFunding.ToBi.dto.Home;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String nickname;

    private List<AccountListResponseDto> accounts;

    public UserResponseDto(final String nickname, final List<AccountListResponseDto> accounts){
        this.nickname = nickname;
        this.accounts = accounts;
    }

}
