package com.munggle.post.service;

import com.munggle.domain.exception.TagNotFoundException;
import com.munggle.domain.model.entity.*;
import com.munggle.post.dto.response.PostListDto;
import com.munggle.post.mapper.PostMapper;
import com.munggle.post.repository.PostRepository;
import com.munggle.post.repository.TagRepository;
import com.munggle.post.repository.UserRecentTagCacheRepository;
import com.munggle.post.repository.UserRecentTagRepository;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.TAG_NOT_FOUND;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class CuratingServiceImpl implements CuratingService {

    private final UserRecentTagCacheRepository userRecentTagCacheRepository;
    private final UserRecentTagRepository userRecentTagRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    /**
     * cache에서 tag를 가지고 와서 랜덤으로 5개 추출
     *
     * @param userId
     * @return
     */
    public List<String> getTagList(Long userId) {
        // userId에 해당하는 최근 태그를 가져오기
        List<UserRecentTagCache> recentTags = userRecentTagCacheRepository.findByUserId(userId, PageRequest.of(0, 30));

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

    /**
     * 주기적으로 cache에 있는 데이터 db에 저장
     */
    @Scheduled(fixedDelay = 600000) // 10분(밀리초 단위)마다 실행
    public void migrateRecentTags() {
        
        List<UserRecentTagCache> recentTags = userRecentTagCacheRepository.findAll();

        // 사용자 최신 태그 리스트들을 db에 저장
        for (UserRecentTagCache recentTag : recentTags) {
            migrateTag(recentTag);
        }
    }

    @Transactional
    public void migrateTag(UserRecentTagCache recentTag) {
        User user = userRepository.findByIdAndIsEnabledTrue(recentTag.getUserId())
                .orElseThrow(() -> new RuntimeException());
        Tag tag = tagRepository.findById(recentTag.getTagId())
                .orElseThrow(() -> new RuntimeException());
        UserRecentTag userRecentTag = UserRecentTag.builder()
                .user(user).tag(tag).build();

        userRecentTagRepository.save(userRecentTag);
    }

    public List<PostListDto> getPostCuratingList(Long userId) {

        List<String> tags = getTagList(userId);
        List<Post> getPost;
        if (tags.isEmpty()) { // 존재하는 tag가 없다면 추천순으로 게시글 정렬
            getPost = postRepository.findAllOrderByLikeCntDesc();
        } else { // 있으면 태그가 존재하는 게시글 추천순으로 정렬
            getPost = postRepository.findByTagsInOrderByLikeCntDesc(tags);
        }

        List<PostListDto> postList = getPost.stream()
                .map(post -> PostMapper.toPostListDto(post))
                .collect(Collectors.toList());

        return postList;
    }
}
