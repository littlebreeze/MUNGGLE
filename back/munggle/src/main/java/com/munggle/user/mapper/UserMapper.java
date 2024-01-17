package com.munggle.user.mapper;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.type.Role;
import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserInfoDto;
import com.munggle.user.dto.UserSearchListDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public static UserInfoDto toUserInfoDto(User user) {
        return UserInfoDto.builder()
                .nickname(user.getNickname())
                .desc(user.getDescription())
                .build();
    }

    public static List<UserSearchListDto> fromUsers(List<User> users) {
        return users.stream()
                .map(UserSearchListDto::toUserSearchListDto)
                .collect(Collectors.toList());
    }
}