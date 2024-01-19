package com.munggle.walk.service;

import com.munggle.domain.model.entity.Location;
import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.repository.LocationRepository;
import com.munggle.walk.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService{

    private final WalkRepository walkRepository;
    private final LocationRepository locationRepository;

    @Override
    public void createWalk(Walk walkCreateDto) {
        walkRepository.save(walkCreateDto);
        for(Location location : walkCreateDto.getLocation()){
            locationRepository.save(location);
        }
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
