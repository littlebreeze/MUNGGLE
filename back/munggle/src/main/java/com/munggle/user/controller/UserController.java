package com.munggle.user.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.user.dto.*;
import com.munggle.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getMemberInfo(@AuthenticationPrincipal User principal) {
        Long id = principal.getId();
        return memberService.getMemberInfo(id);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public void joinMember(@Valid @RequestBody UserCreateDto userCreateDto) {
        memberService.joinMember(userCreateDto);
    }

    @PutMapping("/nickname")
    @ResponseStatus(HttpStatus.OK)
    public void updateNickname(@AuthenticationPrincipal User principal,
                               @RequestBody @Valid UpdateNicknameDto updateNicknameDto) {
        Long id = principal.getId();
        memberService.updateNickname(id, updateNicknameDto.getNewNickname());
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@AuthenticationPrincipal User principal,
                               @RequestBody @Valid UpdatePwdDto updatePwdDto) {
        Long id = principal.getId();
        memberService.updatePassword(id, updatePwdDto.getNewPassword());
    }

    @PutMapping("/desc")
    @ResponseStatus(HttpStatus.OK)
    public void writeDescription(@AuthenticationPrincipal User principal,
                                 @RequestBody @Valid UserDescriptionDto userDescriptionDto) {
        Long id = principal.getId();
        memberService.writeDescription(id, userDescriptionDto.getDescription());
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@AuthenticationPrincipal User principal) {
        Long id = principal.getId();
        memberService.deleteMember(id);
    }
}