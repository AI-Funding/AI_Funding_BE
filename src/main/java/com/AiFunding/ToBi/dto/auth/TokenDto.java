package com.AiFunding.ToBi.dto.auth;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    private String accessToken;
    private String refreshToken;

}
