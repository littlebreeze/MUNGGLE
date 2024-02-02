package com.munggle.walk.mapper;

import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.LocationsNotFoundException;
import com.munggle.domain.model.entity.Location;
import com.munggle.domain.model.entity.Walk;
import com.munggle.user.mapper.UserMapper;
import com.munggle.walk.dto.LocationDto;
import com.munggle.walk.dto.WalkCreateDto;
import com.munggle.walk.dto.WalkDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WalkMapper {

    public static Walk toEntity(WalkCreateDto walkDto){

        return Walk.builder()
                .walkName(walkDto.getWalkName())
                .description(walkDto.getDescription())
                .duration(walkDto.getDuration())
                .distance(walkDto.getDistance())
                .rating(walkDto.getRating())
                // LocationDto 리스트를 Location 리스트로 변환
                .location(walkDto.getLocation().stream()
                        .map(LocationDto::toEntity)
                        .collect(Collectors.toList())
                )
                .isDeleted(false)
                .build();
    }

    // Entity -> Dto
    public static WalkDto toDto(Walk walk){
        return WalkDto.builder()
                .walkId(walk.getWalkId())
                .walkName(walk.getWalkName())
                .dogId(walk.getDog().getDogId())
                .description(walk.getDescription())
                .duration(walk.getDuration())
                .distance(walk.getDistance())
                .rating(walk.getRating())
                .location(walk.getLocation().stream()
                        .map(Location::toDto)
                        .collect(Collectors.toList()))
                .user(UserMapper.toUserProfileDto(walk.getUser()))
                .build();
    }
}
