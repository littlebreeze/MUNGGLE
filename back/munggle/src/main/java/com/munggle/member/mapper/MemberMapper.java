package com.munggle.member.mapper;

import com.munggle.domain.model.entity.Member;
import com.munggle.domain.model.entity.Role;
import com.munggle.member.dto.MemberCreateDto;
import com.munggle.member.dto.MemberInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static Member toEntity(MemberCreateDto memberCreateDto) {
        return Member.builder()
                .username(memberCreateDto.getUsername())
                .password(memberCreateDto.getPassword())
                .nickname(memberCreateDto.getNickname())
                .role(Role.USER)
                .isDeleted(false)
                .build();
    }

    public static MemberInfoDto toMemberInfoDto(Member member) {
        return MemberInfoDto.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .role(member.getRole().name())
                .build();
    }
}