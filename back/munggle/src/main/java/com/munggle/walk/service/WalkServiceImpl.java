package com.munggle.walk.service;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkCreateDto;
import com.munggle.walk.dto.WalkUpdateDto;
import com.munggle.walk.repository.LocationRepository;
import com.munggle.walk.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService{

    private WalkRepository walkRepository;
    private LocationRepository locationRepository;

    @Override
    public void createWalk(WalkCreateDto walkCreateDto) {
        
    }

    @Override
    public void updateWalk(WalkUpdateDto walkUpdateDto) {

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
