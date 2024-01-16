package com.munggle.member.service;

import com.munggle.member.dto.MemberCreateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    void joinMember(MemberCreateDto memberCreateDto);
}
