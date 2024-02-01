package com.munggle.search.service;

import com.munggle.search.dto.SearchByTitleDto;

import java.util.List;

public interface SearchService {

    List<SearchByTitleDto> searchByTitle();
}
