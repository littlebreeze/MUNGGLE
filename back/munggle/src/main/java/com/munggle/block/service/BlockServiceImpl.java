package com.munggle.block.service;

import com.munggle.block.mapper.BlockMapper;
import com.munggle.block.repository.BlockRepository;
import com.munggle.domain.exception.SelfInteractionException;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.Block;
import com.munggle.domain.model.entity.BlockId;
import com.munggle.domain.model.entity.User;
import com.munggle.user.dto.UserListDto;
import com.munggle.user.mapper.UserMapper;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.munggle.domain.exception.ExceptionMessage.SELF_BLOCK;
import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockSerivce{

    private final BlockRepository blockRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserListDto> getBlockUserLsit(Long fromUserId) {
        List<User> users = blockRepository.findByBlockFromIdAndIsBlockedTrue(fromUserId)
                .stream()
                .map(Block::getBlockTo)
                .toList();

        return UserMapper.fromUsers(users);
    }

    @Override
    @Transactional
    public void blockUser(Long fromUserId, Long toUserId) {
        // 자기 자신을 차단하는지 검증
        Optional.of(fromUserId)
                .filter(id -> !id.equals(toUserId))
                .orElseThrow(() -> new SelfInteractionException(SELF_BLOCK));
        // 회원 검증
        User fromUser = userRepository.findByIdAndIsEnabledTrue(fromUserId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        User toUser = userRepository.findByIdAndIsEnabledTrue(toUserId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        //차단과정
        BlockId blockId = BlockMapper.toBlockId(fromUserId, toUserId);
        Block block = blockRepository.findById(blockId)
                .orElseGet(() -> blockRepository.save(
                        Block.builder()
                                .id(blockId)
                                .blockFrom(fromUser)
                                .blockTo(toUser)
                                .build()
                ));

        block.block();
    }
}
