package com.munggle.post.service;

import com.munggle.domain.exception.TagNotFoundException;
import com.munggle.domain.model.entity.*;
import com.munggle.follow.retpository.FollowRepository;
import com.munggle.follow.service.FollowService;
import com.munggle.post.dto.response.PagePostDto;
import com.munggle.post.dto.response.PostListDto;
import com.munggle.post.mapper.PostMapper;
import com.munggle.post.repository.PostLikeRespository;
import com.munggle.post.repository.PostRepository;
import com.munggle.post.repository.TagRepository;
import com.munggle.post.repository.UserRecentTagCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.TAG_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostListServiceImpl implements PostListService {

    private final UserRecentTagCacheRepository userRecentTagCacheRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final FollowRepository followRepository;
    private final PostLikeRespository postLikeRespository;
    private final FollowService followService;

    /**
     * cache에서 tag를 가지고 와서 랜덤으로 5개 추출
     *
     * @param userId
     * @return
     */
    public List<String> getTagList(Long userId) {

        // userId에 해당하는 최근 태그를 가져오기
        List<UserRecentTagCache> recentTags = (List<UserRecentTagCache>) userRecentTagCacheRepository.findByUserId(userId);
        if(recentTags.isEmpty()) {
            log.info("recentTags is EMPTY");
        }

        // 각 태그에 대한 tagId 추출
        List<Long> tagIds = recentTags.stream()
                .map(UserRecentTagCache::getTagId)
                .collect(Collectors.toList());

        // tagId를 이용하여 tagNm을 가져오기
        Set<String> tagNames = tagIds.stream()
                .map(tagId -> tagRepository.findById(tagId)
                        .map(Tag::getTagNm)
                        .orElseThrow(() -> new TagNotFoundException(TAG_NOT_FOUND)))
                .collect(Collectors.toSet());

        // tagNames에서 랜덤으로 5개 이하의 태그 선택
        List<String> randomTags = new ArrayList<>(tagNames);
        Collections.shuffle(randomTags);
        List<String> selectedTags = randomTags.subList(0, Math.min(randomTags.size(), 5));

        return selectedTags;
    }


    /**
     * 최근 확인 태그 저장
     *
     * @param userId
     * @param tagId
     */
    @Transactional
    public void saveRecentTag(Long userId, Long tagId) {
        UserRecentTagCache newUserTag = userRecentTagCacheRepository.findByUserIdAndTagId(userId, tagId)
                                .orElseGet(() -> UserRecentTagCache.builder()
                                .userId(userId).tagId(tagId).build());

        userRecentTagCacheRepository.save(newUserTag);
    }


    public List<PostListDto> getPostCuratingList(Long userId) {

        List<String> tags = getTagList(userId);
        List<Post> getPost;
        if (tags.isEmpty()) { // 존재하는 tag가 없다면 추천순으로 게시글 정렬
            log.info("isEmpty");
            getPost = postRepository.findAllAndNotMineOrderByLikeCntDesc(userId);
        } else { // 있으면 태그가 존재하는 게시글 추천순으로 정렬
            getPost = postRepository.findByTagsAndNotMineOrderByLikeCntDesc(tags, userId);
        }

        List<PostListDto> postList = getPost.stream()
                .map(post -> {
                    // 좋아요 여부 확인
                    PostLikeId postLikeId = PostMapper.toPostLikedIdEntity(userId, post.getId());
                    Boolean isLiked = postLikeRespository.existsByPostLikeIdAndIsDeletedFalse(postLikeId);
                    Boolean isFollowed = followService.checkIsFollowed(userId, post.getUser().getId());

                    return PostMapper.toPostListDto(post, isLiked, isFollowed);
                })
                .collect(Collectors.toList());


        return postList;
    }

    @Override
    public PagePostDto getFollowingPost(Long userId, Pageable pageable) {
        List<Follow> following = followRepository.findByFollowFromIdAndIsFollowedTrue(userId);
        List<User> followedUsers = following.stream()
                .map(Follow::getFollowTo)
                .collect(Collectors.toList());

        Page<Post> postPage = postRepository.findLatestPostsByUsers(followedUsers, pageable);

        List<PostListDto> posts = postPage.getContent().stream()
                .map(post -> {
                    // 좋아요 여부 확인
                    PostLikeId postLikeId = PostMapper.toPostLikedIdEntity(userId, post.getId());
                    Boolean isLiked = postLikeRespository.existsByPostLikeIdAndIsDeletedFalse(postLikeId);

                    return PostMapper.toPostListDto(post, isLiked, true);
                })
                .collect(Collectors.toList());

        return PagePostDto.builder()
                .posts(posts)
                .last(postPage.isLast())
                .build();
    }
}

