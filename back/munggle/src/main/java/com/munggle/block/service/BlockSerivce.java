package com.munggle.block.service;

import com.munggle.user.dto.UserListDto;

import java.util.List;

public interface BlockSerivce {
    List<UserListDto> getBlockUserLsit(Long fromUserId);

    void blockUser(Long fromBlockId, Long toUserId);

    void unblockUser(Long fromUserId, Long toUserId);
}
