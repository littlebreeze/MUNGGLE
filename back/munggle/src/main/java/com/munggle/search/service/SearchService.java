package com.munggle.search.service;

import com.munggle.search.dto.SearchTagDto;
import com.munggle.search.dto.SearchPostListDto;

import java.util.List;

public interface SearchService {

    List<SearchPostListDto> searchPost(Long userId, String type, String word);

    List<SearchTagDto> searchByTag(String word);

}
