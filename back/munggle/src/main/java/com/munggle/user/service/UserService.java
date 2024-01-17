package com.munggle.user.service;

import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserInfoDto;
import com.munggle.user.dto.UserSearchListDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserInfoDto getMemberInfo(Long id);

    List<UserSearchListDto> getSearchPage(String keyword);

    void joinMember(UserCreateDto userCreateDto);

    void updateNickname(Long id, String newNickname);

    void updatePassword(Long id, String newPassword);

    void writeDescription(Long id, String description);

    void deleteMember(Long id);
}
