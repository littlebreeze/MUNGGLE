package com.munggle.member.service;

import com.munggle.member.dto.MemberCreateDto;

public interface MemberService {
    void joinMember(MemberCreateDto memberCreateDto);
}
