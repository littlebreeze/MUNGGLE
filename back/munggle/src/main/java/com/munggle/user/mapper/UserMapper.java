package com.munggle.user.mapper;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.Role;
import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toEntity(UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.getUsername())
                .password(userCreateDto.getPassword())
                .nickname(userCreateDto.getNickname())
                .role(Role.USER)
                .isEnabled(true)
                .build();
    }

    public static UserInfoDto toMemberInfoDto(User user) {
        return UserInfoDto.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .role(user.getRole().name())
                .build();
    }
}