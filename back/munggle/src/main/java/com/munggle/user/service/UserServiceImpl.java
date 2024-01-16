package com.munggle.user.service;

import com.munggle.domain.model.entity.User;
import com.munggle.user.dto.UserCreateDto;
import com.munggle.user.dto.UserInfoDto;
import com.munggle.user.mapper.UserMapper;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private User findMemberById(Long id) {
        return userRepository.findByIdAndIsEnabledFalse(id)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndIsEnabledFalse(username)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));
    }

    public UserInfoDto getMemberInfo(Long id) {
        User user = findMemberById(id);
        return UserMapper.toMemberInfoDto(user);
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
}
