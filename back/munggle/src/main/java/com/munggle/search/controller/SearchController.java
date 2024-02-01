package com.munggle.search.controller;

import com.munggle.search.dto.SearchByTitleDto;
import com.munggle.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/title/{word}")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchByTitleDto> searchPostByTitle(@PathVariable(value = "word", required = false) String word) {
        return searchService.searchByTitle();
    }


}
