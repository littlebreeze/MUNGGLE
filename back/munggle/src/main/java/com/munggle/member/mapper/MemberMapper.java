package com.munggle.member.mapper;

import com.munggle.domain.model.entity.Member;
import com.munggle.domain.model.entity.Role;
import com.munggle.member.dto.MemberCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static Member toEntity(MemberCreateDto memberCreateDto) {
        return Member.builder()
                .email(memberCreateDto.getUsername())
                .password(memberCreateDto.getPassword())
                .nickname(memberCreateDto.getNickname())
                .role(Role.USER)
                .is_deleted(false)
                .build();
    }
}