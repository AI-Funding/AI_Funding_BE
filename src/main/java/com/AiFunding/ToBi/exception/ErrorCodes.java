package com.AiFunding.ToBi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    NOT_EXIST_USER("존재하지 않는 회원입니다.", 10000),
    NOT_EXIST_BOARD("존재하지 않는 게시판입니다.", 10001);


    private String errorMessage;
    private Integer errorCode;
}
