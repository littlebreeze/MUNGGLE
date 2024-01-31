package com.munggle.user.service;

import com.munggle.user.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserMyPageDto getUserMypage(Long id);

    UserProfileDto getUserProfile(Long id);

    List<UserListDto> getSearchPage(String keyword);

    void verify(String email, String autoCode);

    void sendCodeToEmail(String email);

    void joinMember(UserCreateDto userCreateDto);

    void changeBackgroundImage(Long id, MultipartFile file);

    void changeProfileImage(Long id, MultipartFile file);

    void updateProfile(Long id, UpdateProfileDto updateProfileDto);

    void updatePassword(Long id, UpdatePasswordDto updatePasswordDto);

    void deleteMember(Long id);

    void deleteBackgroundImage(Long id);

    void deleteProfileImage(Long id);

}
