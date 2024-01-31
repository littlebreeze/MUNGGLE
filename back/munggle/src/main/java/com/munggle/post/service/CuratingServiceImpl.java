package com.munggle.post.service;

import com.munggle.domain.model.entity.Tag;
import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.UserRecentTag;
import com.munggle.domain.model.entity.UserRecentTagCache;
import com.munggle.post.repository.TagRepository;
import com.munggle.post.repository.UserRecentTagCacheRepository;
import com.munggle.post.repository.UserRecentTagRepository;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class CuratingServiceImpl implements CuratingService {

    private final UserRecentTagCacheRepository userRecentTagCacheRepository;
    private final UserRecentTagRepository userRecentTagRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Override
    public void saveViewPostTag(Long userId, Long postId) {

    }

    /**
     * cache에 있는 데이터 가져오기
     *
     * @param userId
     * @return
     */
    public List<UserRecentTagCache> getRecentTagsByUserId(Long userId) {
        return userRecentTagCacheRepository.findByUserId(userId, PageRequest.of(0, 30));
    }

    /**
     * 최근 확인 태그 저장
     *
     * @param userId
     * @param tagId
     */
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
}
