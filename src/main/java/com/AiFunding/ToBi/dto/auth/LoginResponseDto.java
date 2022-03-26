package com.AiFunding.ToBi.dto.auth;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {
    private String accessToken;

    private String UID;

    private String expireTime; //리프레시 만료시간
}
