package com.munggle.domain.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    USER_NOT_FOUND("회원을 찾을 수 없습니다."),
    NICKNAME_ILLEGAL("닉네임이 올바르지 않습니다."),
    PASSWORD_ILLEGAL("비밀번호는 8자리에서 15자리여야 합니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
