package com.munggle.member.service;

import com.munggle.member.dto.MemberCreateDto;
import com.munggle.member.dto.MemberInfoDto;
import com.munggle.member.dto.UpdateNicknameDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    MemberInfoDto getMemberInfo(Long id);

    void joinMember(MemberCreateDto memberCreateDto);

    void updateNickname(Long id, String newNickname);

    void updatePassword(Long id, String newPassword);

    void writeDescription(Long id, String description);

    void deleteMember(Long id);
}
