package com.munggle.search.service;

import com.munggle.search.dto.SearchTagDto;
import com.munggle.search.dto.SearchPostListDto;

import java.util.List;

public interface SearchService {

    List<SearchPostListDto> searchByTitle(Long userId, String word);

    List<SearchPostListDto> searchByTag(Long userId, String word);

    List<SearchTagDto> searchByTag();

}
