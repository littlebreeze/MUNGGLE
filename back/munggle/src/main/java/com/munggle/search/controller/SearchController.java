package com.munggle.search.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.search.dto.SearchTagDto;
import com.munggle.search.dto.SearchPostListDto;
import com.munggle.search.service.SearchService;
import com.munggle.user.dto.UserListDto;
import com.munggle.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final UserService userService;

    @GetMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchPostListDto> searchPost(@AuthenticationPrincipal User principal,
                                              @RequestParam(value = "type", required = false) String type,
                                              @RequestParam(value = "word", required = false) String word) {

        Long userId = principal.getId();
        return searchService.searchPost(userId, type, word);
    }

    @GetMapping("/tag/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchTagDto> searchPostByTag(@PathVariable(value = "word", required = false) String word) {
        return searchService.searchByTag(word);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserListDto> searchUserByNickname(@RequestParam("keyword") String keyword) {
        return userService.getSearchPage(keyword);
    }
}
