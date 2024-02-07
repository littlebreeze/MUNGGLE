package com.munggle.follow.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.follow.service.FollowService;
import com.munggle.user.dto.UserListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follower/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserListDto> getFollowerList(@PageableDefault(size = 8) Pageable pageable, @PathVariable Long userId) {
        return followService.getFollowerList(userId, pageable);
    }

    @GetMapping("/following/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserListDto> getFollowingList(@PageableDefault(size = 8) Pageable pageable, @PathVariable Long userId) {
        return followService.getFollowingList(userId, pageable);
    }

    @GetMapping("/follower/{userId}/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer getFollowerCount(@PathVariable Long userId) {
        return followService.getFollowerCount(userId);
    }

    @GetMapping("/following/{userId}/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer getFollowingCount(@PathVariable Long userId) {
        return followService.getFollowingCount(userId);
    }

    @PostMapping("/{targetUserId}")
    @ResponseStatus(HttpStatus.OK)
    public void followUser(@PathVariable Long targetUserId, @AuthenticationPrincipal User principal) {
        Long fromUserId = principal.getId();
        followService.followUser(fromUserId, targetUserId);
    }

    @DeleteMapping("/{targetUserId}")
    @ResponseStatus(HttpStatus.OK)
    public void unfollowUser(@PathVariable Long targetUserId, @AuthenticationPrincipal User principal) {
        Long fromUserId = principal.getId();
        followService.unfollow(fromUserId, targetUserId);
    }

    @DeleteMapping("/follower/{followerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFollower(@PathVariable Long followerId, @AuthenticationPrincipal User principal) {
        Long myId = principal.getId();
        followService.deleteFollower(myId, followerId);
    }
}
