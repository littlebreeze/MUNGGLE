package com.munggle.userpage.controller;


import com.munggle.dog.dto.DogDetailDto;
import com.munggle.domain.model.entity.User;
import com.munggle.user.dto.UserProfileDto;
import com.munggle.user.service.UserService;
import com.munggle.userpage.dto.UserPostListDto;
import com.munggle.userpage.dto.UserScrapListDto;
import com.munggle.userpage.service.UserpageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userpages")
@RequiredArgsConstructor
public class UserpageController {

    private final UserpageService userpageService;
    private final UserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto getUserProfile(@AuthenticationPrincipal User principal,
                                         @PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    @GetMapping("/{userId}/post")
    @ResponseStatus(HttpStatus.OK)
    public List<UserPostListDto> getUserPostList(@AuthenticationPrincipal User principal,
                                                 @PathVariable Long userId) {
        return userpageService.getUserPost(userId, principal.getId());
    }

    @GetMapping("/{userId}/scrap")
    @ResponseStatus(HttpStatus.OK)
    public List<UserScrapListDto> getUserScrapList(@AuthenticationPrincipal User principal,
                                                   @PathVariable Long userId) {
        return userpageService.getUserScrap(userId, principal.getId());
    }

    @GetMapping("/{userId}/dog")
    @ResponseStatus(HttpStatus.OK)
    public List<DogDetailDto> getUserDogs(@PathVariable Long userId){
        return userpageService.getDogList(userId);
    }

}
