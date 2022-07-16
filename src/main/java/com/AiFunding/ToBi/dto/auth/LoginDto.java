package com.AiFunding.ToBi.dto.auth;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {
    private String accessToken;

    private String UID;

    private boolean isExistUser;
}
