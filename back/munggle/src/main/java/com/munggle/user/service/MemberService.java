package com.munggle.user.service;

import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserInfoDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    UserInfoDto getMemberInfo(Long id);

    void joinMember(UserCreateDto userCreateDto);

    void updateNickname(Long id, String newNickname);

    void updatePassword(Long id, String newPassword);

    void writeDescription(Long id, String description);

    void deleteMember(Long id);
}
