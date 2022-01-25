package com.AiFunding.ToBi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HomeDto {

    private String nickname;

    private List<AccountListDto> accounts;

    public HomeDto(final String nickname, final List<AccountListDto> accounts){
        this.nickname = nickname;
        this.accounts = accounts;
    }

}
