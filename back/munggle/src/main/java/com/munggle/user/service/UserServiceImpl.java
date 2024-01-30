package com.munggle.user.service;

import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.User;
import com.munggle.email.service.EmailService;
import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserMyPageDto;
import com.munggle.user.dto.UserProfileDto;
import com.munggle.user.dto.UserListDto;
import com.munggle.user.mapper.UserMapper;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import static com.munggle.domain.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;
    private final EmailService emailService;
    private final UserRepository userRepository;

    private User findMemberById(Long id) {
        return userRepository.findByIdAndIsEnabledTrue(id)
                        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndIsEnabledTrue(username)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public UserMyPageDto getUserMypage(Long id) {
        User user = findMemberById(id);
        return UserMapper.toUserMyPageDto(user);
    }

    public UserProfileDto getUserProfile(Long id) {
        User user = findMemberById(id);
        return UserMapper.toUserProfileDto(user);
    }

    @Override
    public List<UserListDto> getSearchPage(String keyword) {
        List<User> userList = userRepository.findByNicknameContainingAndIsEnabledTrue(keyword);
        return UserMapper.fromUsers(userList);
    }

    @Override
    public void sendCodeToEmail(String toEmail) {
        String title = "멍글멍글 이메일 인증 번호";
        String authCode = this.createCode();
        emailService.sendEmail(toEmail, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
    }

    @Override
    @Transactional
    public void joinMember(UserCreateDto userCreateDto) {
        User newUser = UserMapper.toEntity(userCreateDto);
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void updateNickname(Long id, String newNickname) {
        User user = findMemberById(id);
        user.changeNickname(newNickname);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, String newPassword) {
        User user = findMemberById(id);
        user.changePassword(newPassword);
    }

    @Override
    @Transactional
    public void writeDescription(Long id, String description) {
        User user = findMemberById(id);
        user.writeDescription(description);
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        User user = findMemberById(id);
        user.markAsDeleted();
    }

    private String createCode() {
        Integer length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
