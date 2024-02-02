package com.munggle.search.service;

import com.munggle.post.repository.PostRepository;
import com.munggle.search.dto.SearchTagDto;
import com.munggle.search.dto.SearchPostListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final PostRepository postRepository;

    @Override
    public List<SearchPostListDto> searchByTitle(Long userId, String word) {

        return null;
    }

    @Override
    public List<SearchPostListDto> searchByTag(Long userId, String word) {
        return null;
    }

    @Override
    public List<SearchTagDto> searchByTag() {


        return null;
    }


}
