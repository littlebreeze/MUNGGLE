package com.munggle.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserInfoDto {

    private final String username;
    private final String nickname;
    private final String role;
}