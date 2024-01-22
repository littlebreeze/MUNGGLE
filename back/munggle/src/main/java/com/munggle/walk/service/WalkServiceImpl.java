package com.munggle.walk.service;

import com.munggle.domain.model.entity.Location;
import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.LocationDto;
import com.munggle.walk.dto.WalkDto;
import com.munggle.walk.mapper.WalkMapper;
import com.munggle.walk.repository.LocationRepository;
import com.munggle.walk.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Walk> readMyWalks(String userId) {
        return null;
    }

    @Override
    public List<Walk> readLocationWalks(Float latitude, Float Longitude) {
        return null;
    }

    @Override
    public void deleteWalk(Long walkId) {

    }

    @Override
    public void detailWalk(Long walkId) {

    }
}
