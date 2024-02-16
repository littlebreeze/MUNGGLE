package com.munggle.search.service;

import com.munggle.domain.exception.IllegalSearchTypeException;
import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.PostLikeId;
import com.munggle.domain.model.entity.Tag;
import com.munggle.follow.service.FollowService;
import com.munggle.post.mapper.PostMapper;
import com.munggle.post.repository.PostLikeRespository;
import com.munggle.post.repository.PostRepository;
import com.munggle.post.repository.TagRepository;
import com.munggle.search.dto.SearchPagePostDto;
import com.munggle.search.dto.SearchTagDto;
import com.munggle.search.dto.SearchPostListDto;
import com.munggle.search.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final FollowService followService;

    @Override
    public List<SearchTagDto> searchByTag(String word) {

        List<Tag> tagList = tagRepository.searchByTagNm(word);

        return tagList.stream()
                .map(SearchMapper::toSearchTagDto)
                .collect(Collectors.toList());
    }

    @Override
    public SearchPagePostDto searchPagePost(Long userId, String type, String word, Pageable pageable) {
        Page<Post> postPage;
        if (type.equals("title")) {
            postPage = postRepository.searchByPostTitlePage(word, pageable);
        } else if (type.equals("tag")) {
            postPage = postRepository.searchByTagNmPage(word, pageable);
        } else {
            throw new IllegalSearchTypeException(SEARCH_TYPE_ILLEGAL);
        }

        List<SearchPostListDto> posts = postPage.getContent().stream()
                .map(post -> {
                    // 좋아요 여부 확인
                    PostLikeId postLikeId = PostMapper.toPostLikedIdEntity(userId, post.getId());
                    Boolean isLiked = postLikeRespository.existsByPostLikeIdAndIsDeletedFalse(postLikeId);
                    // 내 게시글 인지 확인
                    Boolean isMine = post.getUser().getId().equals(userId);
                    // 팔로우 여부 확인
                    Boolean isFollowed = followService.checkIsFollowed(userId, post.getUser().getId());

                    return SearchMapper.toSearchPostListDto(post, isLiked, isMine, isFollowed);
                })
                .collect(Collectors.toList());

        return SearchPagePostDto.builder()
                .posts(posts)
                .last(postPage.isLast())
                .build();
    }


}
