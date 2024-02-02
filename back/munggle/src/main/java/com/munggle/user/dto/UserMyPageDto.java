package com.munggle.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserMyPageDto {

    private final String backgroundImgUrl;

    private final String profileImgUrl;

    @NotNull
    private final String username;

    @NotNull
    private final String nickname;

    private final String description;

    private final String role;
}
