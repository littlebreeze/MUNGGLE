package com.munggle.user.dto;

import com.munggle.domain.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSearchListDto {

    @NotBlank
    private final String nickname;

    // 추후 추가
//    private final String profileImgUrl;

    public static UserSearchListDto toUserSearchListDto(User user) {
        return UserSearchListDto.builder()
                .nickname(user.getNickname())
                .build();
    }
}
