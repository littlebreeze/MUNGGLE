package com.munggle.member.service;

import com.munggle.member.dto.MemberCreateDto;
import com.munggle.member.dto.MemberInfoDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    MemberInfoDto findMemberById(Long id);

    void joinMember(MemberCreateDto memberCreateDto);
}
