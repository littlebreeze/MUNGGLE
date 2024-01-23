package com.munggle.walk.service;

import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.LocationsNotFoundException;
import com.munggle.domain.exception.WalkNotFoundException;
import com.munggle.domain.model.entity.Location;
import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.LocationDto;
import com.munggle.walk.dto.WalkDto;
import com.munggle.walk.dto.WalkUpdateDto;
import com.munggle.walk.mapper.WalkMapper;
import com.munggle.walk.repository.LocationRepository;
import com.munggle.walk.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService{

    private final WalkRepository walkRepository;
    private final LocationRepository locationRepository;

    @Override
    public void createWalk(WalkDto walkDto) {

        Walk walk = WalkMapper.toEntity(walkDto);
        Long insertID = walkRepository.save(walk).getWalkId();
        // DB에 넣을 때, 방금 생성된 Walk의 id가 들어가야 하므로 값 셋팅된 객체로 다시 build
        locationRepository.saveAll(walk.getLocation().stream()
                .map(location -> location.setInsertId(insertID)).collect(Collectors.toList()));
    }

    @Override
    public void updateWalk(Walk walkUpdateDto) {

    }

    @Override
    public List<WalkDto> readMyWalks(Long userId) {

        List<Walk> result = walkRepository.findAllByUserIdAndIsDeletedFalse(userId).orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));
        List<WalkDto> list = new ArrayList<>();
        for(Walk walk : result){
            walk.setLocations(locationRepository.findAllByWalkWalkId(walk.getWalkId()).orElseThrow(()->new LocationsNotFoundException(ExceptionMessage.WALK_LOG_NOT_FOUND)));
            list.add(WalkMapper.toDto(walk));
        }

        return list;
    }

    @Override
    public List<WalkDto> readLocationWalks(Float lat, Float lng) {
        return null;
    }

    @Override
    public WalkDto detailWalk(Long walkId) {
        Walk walk = walkRepository.findByWalkIdAndIsDeletedFalse(walkId).orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));
        walk.setLocations(locationRepository.findAllByWalkWalkId(walk.getWalkId()).orElseThrow(()->new LocationsNotFoundException(ExceptionMessage.WALK_LOG_NOT_FOUND)));
        return WalkMapper.toDto(walk);
    }

    @Override
    @Transactional
    public WalkDto updateWalk(WalkUpdateDto walkUpdateDto) {

        Walk walk = walkRepository.findById(walkUpdateDto.getWalkId()).orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));
        walk.updateWalk(walkUpdateDto);

        return null;
    }

    @Override
    @Transactional
    public void deleteWalk(Long walkId) {
        Walk walk = walkRepository.findById(walkId).orElseThrow();
        walk.setDeleted();
    }
}
