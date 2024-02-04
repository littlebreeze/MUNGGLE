package com.munggle.search.service;

import com.munggle.domain.exception.IllegalSearchTypeException;
import com.munggle.domain.model.entity.Post;
import com.munggle.post.repository.PostRepository;
import com.munggle.search.dto.SearchTagDto;
import com.munggle.search.dto.SearchPostListDto;
import com.munggle.search.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.SEARCH_TYPE_ILLEGAL;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final PostRepository postRepository;

    @Override
    public List<SearchPostListDto> searchPost(Long userId, String type, String word) {

        List<Post> postLists;
        if (type.equals("title")) {
            postLists = postRepository.findPostsByPostTitle(word);
        } else if (type.equals("tag")) {
            postLists = postRepository.findPostsByTagNm(word);
        } else {
            throw new IllegalSearchTypeException(SEARCH_TYPE_ILLEGAL);
        }

        return postLists.stream()
                .map(SearchMapper::toSearchPostListDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchTagDto> searchByTag() {


        return null;
    }


}
