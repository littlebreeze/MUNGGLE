package com.munggle.user.dto;

import com.munggle.domain.model.entity.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserMyPageDto {

    private final String username;

    private final String nickname;

    private final String description;

    private final String role;
}
