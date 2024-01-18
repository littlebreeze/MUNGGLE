package com.munggle.walk.mapper;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WalkMapper {

    public static Walk toEntity(WalkCreateDto walkCreateDto){
        return Walk.builder()
                .userId(walkCreateDto.getUserId())
                .dogId(walkCreateDto.getDogId())
                .isDeleted(false)
                .build();
    }
}
