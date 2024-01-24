package com.munggle.follow.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("{targetUserId}")
    @ResponseStatus(HttpStatus.OK)
    public void followUser(@PathVariable Long targetUserId, @AuthenticationPrincipal User principal) {
        Long fromUserId = principal.getId();
        followService.followUser(fromUserId, targetUserId);
    }
}
