package com.munggle.walk.service;

import com.munggle.dog.repository.DogRepository;
import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.LocationsNotFoundException;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.exception.WalkNotFoundException;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Location;
import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.Walk;
import com.munggle.user.repository.UserRepository;
import com.munggle.walk.dto.*;
import com.munggle.walk.mapper.WalkMapper;
import com.munggle.walk.repository.LocationRepository;
import com.munggle.walk.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService{

    private final WalkRepository walkRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final DogRepository dogRepository;

    @Override
    public void createWalk(WalkCreateDto walkDto) {

        Walk walk = WalkMapper.toEntity(walkDto);

        // Dto로 넘어온 userId로 user 세팅
        User user = userRepository.findByIdAndIsEnabledTrue(walkDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        walk.setUser(user);

        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(walkDto.getDogId())
                .orElseThrow();//()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        walk.setDog(dog);

        Long insertID = walkRepository.save(walk).getWalkId();
        // DB에 넣을 때, 방금 생성된 Walk의 id가 들어가야 하므로 값 셋팅된 객체로 다시 build
        locationRepository.saveAll(walk.getLocation().stream()
                .map(location -> location.setInsertId(insertID)).collect(Collectors.toList()));
    }

    @Override
    public WalkCalendarDto readMyWalks(Long userId, Integer year, Integer month) {

        Integer cnt = 0;
        Float distance = 0f;
        Integer duration = 0;

        // 요청 월에 대한 기간 설정
        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDateTime start = LocalDateTime.of(year, month,1 ,0,0);
        LocalDateTime end = LocalDateTime.of(year, month, yearMonth.atEndOfMonth().getDayOfMonth(),0,0);

        List<WalkDto> result = walkRepository.findAllByCreatedAtBetween(start, end)
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND))
                .stream().map(walk -> WalkMapper.toDto(walk)).collect(Collectors.toList());

        return WalkCalendarDto.builder()
                .walkList(result)
                .totalCnt(cnt)
                .totalDistance(distance)
                .totalDuration(duration)
                .build();
    }

    @Override
    public List<WalkDto> readLocationWalks(Float lat, Float lng) {

        // 공개 범위 설정한 사용자의 산책 기록만 보내주자~
        // 마커 찍는건 로그 첫번째로!
        List<WalkDto> result = walkRepository.findAllByIsDeletedFalseAndIsPrivatedFalse()
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND))
                .stream().map(walk -> WalkMapper.toDto(walk)).collect(Collectors.toList());

        return result;
    }

    @Override
    public WalkDto detailWalk(Long walkId) {
        Walk walk = walkRepository.findByWalkIdAndIsDeletedFalse(walkId)
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));

        return WalkMapper.toDto(walk);
    }

    @Override
    @Transactional
    public WalkDto updateWalk(WalkUpdateDto walkUpdateDto) {

        Walk walk = walkRepository.findById(walkUpdateDto.getWalkId())
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));
        walk.updateWalk(walkUpdateDto);

        return null;
    }

    @Override
    @Transactional
    public void toggleVisibility(Long walkId) {
        Walk walk = walkRepository.findById(walkId)
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));
        walk.togglePrivated();
    }

    @Override
    @Transactional
    public void deleteWalk(Long walkId) {
        Walk walk = walkRepository.findById(walkId)
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));
        walk.setDeleted();
    }
}
