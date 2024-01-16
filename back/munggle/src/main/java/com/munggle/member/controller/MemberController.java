package com.munggle.member.controller;

import com.munggle.domain.model.entity.Member;
import com.munggle.member.dto.*;
import com.munggle.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public MemberInfoDto getMemberInfo(@AuthenticationPrincipal Member principal) {
        Long id = principal.getId();
        return memberService.findMemberById(id);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public void joinMember(@Valid @RequestBody MemberCreateDto memberCreateDto) {
        memberService.joinMember(memberCreateDto);
    }

    @PutMapping("/nickname")
    @ResponseStatus(HttpStatus.OK)
    public void updateNickname(@AuthenticationPrincipal Member principal,
                               @RequestBody @Valid UpdateNicknameDto updateNicknameDto) {
        Long id = principal.getId();
        memberService.updateNickname(id, updateNicknameDto.getNewNickname());
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@AuthenticationPrincipal Member principal,
                               @RequestBody @Valid UpdatePwdDto updatePwdDto) {
        Long id = principal.getId();
        memberService.updatePassword(id, updatePwdDto.getNewPassword());
    }

    @PutMapping("/desc")
    @ResponseStatus(HttpStatus.OK)
    public void writeDescription(@AuthenticationPrincipal Member principal,
                                 @RequestBody @Valid MemberDescriptionDto memberDescriptionDto) {
        Long id = principal.getId();
        memberService.writeDescription(id, memberDescriptionDto.getDescription());
    }
}