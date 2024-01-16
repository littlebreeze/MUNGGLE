package com.munggle.member.service;

import com.munggle.domain.model.entity.Member;
import com.munggle.member.dto.MemberCreateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    Member findMemberById(Long id);

    void joinMember(MemberCreateDto memberCreateDto);
}
