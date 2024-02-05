package com.munggle.search.service;

import com.munggle.domain.exception.IllegalSearchTypeException;
import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.PostLikeId;
import com.munggle.domain.model.entity.Tag;
import com.munggle.post.mapper.PostMapper;
import com.munggle.post.repository.PostLikeRespository;
import com.munggle.post.repository.PostRepository;
import com.munggle.post.repository.TagRepository;
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
    private final TagRepository tagRepository;
    private final PostLikeRespository postLikeRespository;

    @Override
    public List<SearchPostListDto> searchPost(Long userId, String type, String word) {

        List<Post> postList;
        if (type.equals("title")) {
            postList = postRepository.searchByPostTitle(word);
        } else if (type.equals("tag")) {
            postList = postRepository.searchByTagNm(word);
        } else {
            throw new IllegalSearchTypeException(SEARCH_TYPE_ILLEGAL);
        }

        return postList.stream()
                .map(post -> {
                    // 좋아요 여부 확인
                    PostLikeId postLikeId = PostMapper.toPostLikedIdEntity(userId, post.getId());
                    Boolean isLiked = postLikeRespository.existsByPostLikeIdAndIsDeletedFalse(postLikeId);

                    return SearchMapper.toSearchPostListDto(post, isLiked);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchTagDto> searchByTag(String word) {

        List<Tag> tagList = tagRepository.searchByTagNm(word);

        return tagList.stream()
                .map(SearchMapper::toSearchTagDto)
                .collect(Collectors.toList());
    }


}
