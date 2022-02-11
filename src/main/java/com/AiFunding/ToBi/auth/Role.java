package com.AiFunding.ToBi.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER","인증된 사용자");

    private final String key;
    private final String title;
}
