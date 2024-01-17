package com.munggle.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserInfoDto {

    // 프로필 dto임으로 배경 이미지랑 프로필 이미지 추후 추가
    private final String nickname;
    private final String desc;
}