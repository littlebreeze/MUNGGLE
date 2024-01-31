package com.munggle.domain.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    USER_NOT_FOUND("회원을 찾을 수 없습니다."),
    NICKNAME_ILLEGAL("닉네임이 올바르지 않습니다."),
    PASSWORD_ILLEGAL("비밀번호는 공백이 포함되지 않은 8 ~ 15자리 입니다."),
    SELF_FOLLOW("자신을 팔로우할 수 없습니다."),
    SELF_BLOCK("자신을 차단할 수 없습니다."),
    BLOCK_NOT_FOUND("차단된 회원을 찾을 수 없습니다"),
    FOLLOW_NOT_FOUND("팔로우한 회원을 찾을 수 없습니다."),
    WALK_NOT_FOUND("산책 기록을 찾을 수 없습니다."),
    WALK_LOG_NOT_FOUND("산책 로그를 찾을 수 없습니다."),
    DOG_NOT_FOUND("반려견 정보를 찾을 수 없습니다."),
    MATCHING_CHARACTER_NOT_FOUND("매칭 특성이 등록되지 않았습니다."),
    MATCHING_IS_NOT_ON("매칭 옵션이 켜지지 않았습니다.");


    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
