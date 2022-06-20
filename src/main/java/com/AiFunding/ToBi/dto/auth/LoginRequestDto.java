package com.AiFunding.ToBi.dto.auth;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String nickname;
    private String email;
    private String loginType;
    private String UID;
}
