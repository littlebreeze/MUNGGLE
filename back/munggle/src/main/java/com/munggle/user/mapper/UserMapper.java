package com.munggle.user.mapper;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.type.Role;
import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserMyPageDto;
import com.munggle.user.dto.UserProfileDto;
import com.munggle.user.dto.UserListDto;
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
                .role(Role.MEMBER)
                .isEnabled(true)
                .build();
    }

    public static UserMyPageDto toUserMyPageDto(User user) {
        return UserMyPageDto.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .description(user.getDescription())
                .role(user.getRole() == Role.ADMIN ? "관리자" : "회원")
                .build();
    }

    public static UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .nickname(user.getNickname())
                .desc(user.getDescription())
                .build();
    }

    public static List<UserListDto> fromUsers(List<User> users) {
        return users.stream()
                .map(UserListDto::toUserListDto)
                .collect(Collectors.toList());
    }
}