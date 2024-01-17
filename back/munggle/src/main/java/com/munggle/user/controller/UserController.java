package com.munggle.user.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.user.dto.*;
import com.munggle.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getMemberInfo(@AuthenticationPrincipal User principal) {
        Long id = principal.getId();
        return userService.getMemberInfo(id);
    }

    @GetMapping("/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserSearchListDto> searchUserByNickname(@PathVariable String keyword) {
        return userService.getSearchPage(keyword);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public void joinMember(@Valid @RequestBody UserCreateDto userCreateDto) {
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
                               @RequestBody @Valid UpdatePwdDto updatePwdDto) {
        Long id = principal.getId();
        userService.updatePassword(id, updatePwdDto.getNewPassword());
    }

    @PutMapping("/desc")
    @ResponseStatus(HttpStatus.OK)
    public void writeDescription(@AuthenticationPrincipal User principal,
                                 @RequestBody @Valid UserDescriptionDto userDescriptionDto) {
        Long id = principal.getId();
        userService.writeDescription(id, userDescriptionDto.getDescription());
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@AuthenticationPrincipal User principal) {
        Long id = principal.getId();
        userService.deleteMember(id);
    }
}