package com.munggle.follow.service;

import com.munggle.domain.exception.FollowNotFoundException;
import com.munggle.domain.exception.SelfFollowException;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.Follow;
import com.munggle.domain.model.entity.FollowId;
import com.munggle.domain.model.entity.User;
import com.munggle.follow.Mapper.FollowMapper;
import com.munggle.follow.retpository.FollowRepository;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.munggle.domain.exception.ExceptionMessage.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Override
    @Transactional
    public void followUser(Long fromUserId, Long targetUserId) {
        // 자기 자신을 팔로우하는지 검증
        Optional.of(fromUserId)
                .filter(id -> !id.equals(targetUserId))
                .orElseThrow(()-> new SelfFollowException(SELF_FOLLOW));
        // 받아온 userId 검증
        User fromUser = userRepository.findByIdAndIsEnabledTrue(fromUserId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        User targetUser = userRepository.findByIdAndIsEnabledTrue(targetUserId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        // 팔로우 과정
        FollowId followId = FollowMapper.toFollowId(fromUserId, targetUserId);
        Follow follow = followRepository.findById(followId)
                .orElseGet(() -> followRepository.save(
                        Follow.builder()
                                .followId(followId)
                                .followUser(fromUser)
                                .targetUser(targetUser)
                                .build()
                ));

        follow.follow();
    }

    @Override
    @Transactional
    public void unfollow(Long fromUserId, Long targetUserId) {
        FollowId followId = FollowMapper.toFollowId(fromUserId, targetUserId);
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new FollowNotFoundException(FOLLOW_NOT_FOUND));

        follow.unfollow();
    }

    @Override
    @Transactional
    public void deleteFollower(Long myId, Long followerId) {
        FollowId followId = FollowMapper.toFollowId(followerId, myId);
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new FollowNotFoundException(FOLLOW_NOT_FOUND));

        follow.unfollow();
    }
}
