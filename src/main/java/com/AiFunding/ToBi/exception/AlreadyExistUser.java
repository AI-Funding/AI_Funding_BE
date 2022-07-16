package com.AiFunding.ToBi.exception;

public class AlreadyExistUser extends RuntimeException{
    private static final String MESSAGE = "이미 존재하는 유저입니다.";

    public AlreadyExistUser(){
        super(MESSAGE);
    }
}
