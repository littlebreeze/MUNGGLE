package com.munggle.search.service;

import com.munggle.search.dto.SearchPagePostDto;
import com.munggle.search.dto.SearchTagDto;
import com.munggle.search.dto.SearchPostListDto;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SearchService {

    List<SearchTagDto> searchByTag(String word);

    SearchPagePostDto searchPagePost(Long userId, String type, String word, Pageable pageable);
}
