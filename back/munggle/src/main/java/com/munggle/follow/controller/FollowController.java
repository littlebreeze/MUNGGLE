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

    @DeleteMapping("{targetUserId}")
    @ResponseStatus(HttpStatus.OK)
    public void unfollowUser(@PathVariable Long targetUserId, @AuthenticationPrincipal User principal) {
        Long fromUserId = principal.getId();
        followService.unfollow(fromUserId, targetUserId);
    }

    @DeleteMapping("follower/{followerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFollower(@PathVariable Long followerId, @AuthenticationPrincipal User principal) {
        Long myId = principal.getId();
        followService.deleteFollower(myId, followerId);
    }
}
