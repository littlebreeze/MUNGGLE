package com.munggle.user.service;

import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserMyPageDto;
import com.munggle.user.dto.UserProfileDto;
import com.munggle.user.dto.UserListDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserMyPageDto getUserMypage(Long id);

    UserProfileDto getUserProfile(Long id);

    List<UserListDto> getSearchPage(String keyword);

    void verify(String email, String autoCode);

    void sendCodeToEmail(String email);

    void joinMember(UserCreateDto userCreateDto);

    void updateNickname(Long id, String newNickname);

    void updatePassword(Long id, String newPassword);

    void writeDescription(Long id, String description);

    void deleteMember(Long id);

}
