package com.munggle.walk.mapper;

import com.munggle.domain.model.entity.Location;
import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.LocationDto;
import com.munggle.walk.dto.WalkCreateDto;
import com.munggle.walk.dto.WalkDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WalkMapper {

    public static Walk toEntity(WalkDto walkDto){

        return Walk.builder()
                .walkName(walkDto.getWalkName())
                .userId(walkDto.getUserId())
                .dogId(walkDto.getDogId())
                .description(walkDto.getDescription())
                .duration(walkDto.getDuration())
                .distance(walkDto.getDistance())
                .location(walkDto.getLocation().stream()
                        .map(LocationDto::toEntity)
                        .collect(Collectors.toList())
                )
                .isDeleted(false)
                .build();
    }

    // Entity -> Dto
}
