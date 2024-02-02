package com.munggle.search.service;

import com.munggle.post.repository.PostRepository;
import com.munggle.search.dto.SearchByTagDto;
import com.munggle.search.dto.SearchByTitleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final PostRepository postRepository;

    @Override
    public List<SearchByTitleDto> searchByTitle() {

        return null;
    }

    @Override
    public List<SearchByTagDto> searchByTag() {
        return null;
    }
}
