package com.munggle.block.mapper;

import com.munggle.domain.model.entity.BlockId;

public class BlockMapper {

    public static BlockId toBlockId(Long fromId, Long toId) {
        return BlockId.builder()
                .blockFromId(fromId)
                .blockToId(toId)
                .build();
    }
}
