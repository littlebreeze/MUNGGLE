package com.munggle.domain.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    USER_NOT_FOUND("회원을 찾을 수 없습니다."),
    NICKNAME_ILLEGAL("닉네임에는 공백이 들어갈 수 없습니다."),
    PASSWORD_ILLEGAL("비밀번호에는 공백이 들어갈 수 없습니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
