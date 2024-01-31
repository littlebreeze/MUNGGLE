package com.munggle.user.service;

import com.munggle.domain.exception.PasswordNotConfirmException;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.UserImage;
import com.munggle.image.dto.FileInfoDto;
import com.munggle.image.service.FileS3UploadService;
import com.munggle.user.dto.*;
import com.munggle.user.mapper.UserMapper;
import com.munggle.user.repository.UserImageRepository;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.munggle.domain.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    private final FileS3UploadService fileS3UploadService;

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
    @Transactional
    public void joinMember(UserCreateDto userCreateDto) {
        User newUser = UserMapper.toEntity(userCreateDto);
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void changeBackgroundImage(Long id, MultipartFile file) {
        User user = this.findMemberById(id);
        // 기존 이미지 확인
        Optional.ofNullable(user.getBackgroundImage())
                .map(UserImage::getImageName)
                .ifPresent(imageName -> {
                    fileS3UploadService.removeFile(imageName);
                    userImageRepository.deleteByImageName(imageName);
                });

        // 새로운 이미지 업로드
        String uploadBackPath = user.getId() + "/" + "back" + "/";
        FileInfoDto backFileInfoDto = fileS3UploadService.uploadFile(uploadBackPath, file);

        // UserImage 객체 업데이트 및 새로운 배경화면 저장
        UserImage background = UserMapper.toUserImage(backFileInfoDto, user, "background");
        userImageRepository.save(background);
        user.changeBackgroundImage(background);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void changeProfileImage(Long id, MultipartFile file) {
        User user = this.findMemberById(id);

        Optional.ofNullable(user.getProfileImage())
                .map(UserImage::getImageName)
                .ifPresent(imageName -> {
                    fileS3UploadService.removeFile(imageName);
                    userImageRepository.deleteByImageName(imageName);
                });

        String uploadProfilePath = user.getId() + "/" + "profile" + "/";
        FileInfoDto profileFileInfoDto = fileS3UploadService.uploadFile(uploadProfilePath, file);

        UserImage profileImage = UserMapper.toUserImage(profileFileInfoDto, user, "profile");
        userImageRepository.save(profileImage);
        user.changeProfileImage(profileImage);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateProfile(Long id, UpdateProfileDto updateProfileDto) {
        User user = this.findMemberById(id);
        String newNickname = updateProfileDto.getNewNickname();
        String newDesc = updateProfileDto.getDescription();
        user.changeProfile(newNickname, newDesc);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UpdatePasswordDto updatePasswordDto) {
        User user = findMemberById(id);
        String newPassword = updatePasswordDto.getNewPassword();
        String passwordConfirm = updatePasswordDto.getNewPasswordConfirmation();
        // 비밀번호 확인과 일치하는지 확인
        if (!newPassword.equals(passwordConfirm)) {
            throw new PasswordNotConfirmException(PASSWORD_NOT_CONFIRM);
        }

        user.changePassword(newPassword);
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        User user = findMemberById(id);
        user.markAsDeleted();
    }

    @Override
    @Transactional
    public void deleteBackgroundImage(Long id) {
        User user = this.findMemberById(id);

        Optional.ofNullable(user.getBackgroundImage())
                .map(UserImage::getImageName)
                .ifPresent(imageName -> {
                    fileS3UploadService.removeFile(imageName);  // 이미지 파일 삭제
                    user.changeBackgroundImage(null);  // User 객체에서 UserImage 참조 제거
                    userRepository.save(user);  // User 객체 업데이트
                    userImageRepository.deleteByImageName(imageName);  // UserImage 테이블의 데이터 삭제
                });
    }

    @Override
    @Transactional
    public void deleteProfileImage(Long id) {
        User user = this.findMemberById(id);

        Optional.ofNullable(user.getProfileImage())
                .map(UserImage::getImageName)
                .ifPresent(imageName -> {
                    fileS3UploadService.removeFile(imageName);  // 이미지 파일 삭제
                    user.changeProfileImage(null);  // User 객체에서 UserImage 참조 제거
                    userRepository.save(user);  // User 객체 업데이트
                    userImageRepository.deleteByImageName(imageName);  // UserImage 테이블의 데이터 삭제
                });
    }
}
