package com.munggle.user.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.user.dto.*;
import com.munggle.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/mypage")
    @ResponseStatus(HttpStatus.OK)
    public UserMyPageDto getMyPage(@AuthenticationPrincipal User principal) {
        Long id = principal.getId();
        return userService.getUserMypage(id);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto getMemberInfo(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    @GetMapping("/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserListDto> searchUserByNickname(@PathVariable String keyword) {
        return userService.getSearchPage(keyword);
    }

    @PostMapping("/emails/verification-requests")
    @ResponseStatus(HttpStatus.OK)
    public void sendVerificationMessage(@RequestParam("email") String email) {
        userService.sendCodeToEmail(email);
    }

    @GetMapping("/emails/verifications")
    @ResponseStatus(HttpStatus.OK)
    public void verifyByEmail(@RequestParam("email") String email,
                              @RequestParam("code") String autoCode) {
        userService.verify(email, autoCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void joinMember(@RequestBody @Valid UserCreateDto userCreateDto) {
        userService.joinMember(userCreateDto);
    }

    @PutMapping("/nickname")
    @ResponseStatus(HttpStatus.OK)
    public void updateNickname(@AuthenticationPrincipal User principal,
                               @RequestBody @Valid UpdateNicknameDto updateNicknameDto) {
        Long id = principal.getId();
        userService.updateNickname(id, updateNicknameDto.getNewNickname());
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@AuthenticationPrincipal User principal,
                               @RequestBody @Valid UpdatePasswordDto updatePasswordDto) {
        Long id = principal.getId();
        userService.updatePassword(id, updatePasswordDto.getNewPassword());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void writeDescription(@AuthenticationPrincipal User principal,
                                 @RequestBody @Valid UserDescriptionDto userDescriptionDto) {
        Long id = principal.getId();
        userService.writeDescription(id, userDescriptionDto.getDescription());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@AuthenticationPrincipal User principal) {
        Long id = principal.getId();
        userService.deleteMember(id);
    }
}