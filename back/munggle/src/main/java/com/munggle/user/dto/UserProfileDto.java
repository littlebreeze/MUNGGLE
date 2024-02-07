package com.munggle.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserProfileDto {

    private final Long id;

    private final String backgroundImgUrl;

    private final String profileImgUrl;

    @NotNull
    private final String nickname;

    private final String desc;

    private final Integer followerCount;

    private final Integer followingCount;
}