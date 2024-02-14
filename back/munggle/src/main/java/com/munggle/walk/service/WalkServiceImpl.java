package com.munggle.walk.service;

import com.munggle.dog.repository.DogRepository;
import com.munggle.domain.exception.*;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.Walk;
import com.munggle.image.dto.FileInfoDto;
import com.munggle.image.service.FileS3UploadService;
import com.munggle.user.repository.UserRepository;
import com.munggle.walk.dto.*;
import com.munggle.walk.mapper.WalkMapper;
import com.munggle.walk.repository.LocationRepository;
import com.munggle.walk.repository.WalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService{

    private final FileS3UploadService fileS3UploadService;

    private final WalkRepository walkRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final DogRepository dogRepository;

    String walkFilePath = "walk/";

    public FileInfoDto uploadWalkImage(Long walkId, MultipartFile file){
        // 이미지 정보 저장
        String uploadPath = walkFilePath + walkId + "/";
        FileInfoDto fileInfoDto = fileS3UploadService.uploadFile(uploadPath, file);

        return fileInfoDto;
    }

    @Override
    @Transactional
    public void createWalk(WalkCreateDto walkCreateDto) {

        Walk walk = WalkMapper.toEntity(walkCreateDto);

        // Dto로 넘어온 userId로 user 세팅
        User user = userRepository.findByIdAndIsEnabledTrue(walkCreateDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        walk.setUser(user);

        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(walkCreateDto.getDogId())
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        walk.setDog(dog);

        // 사용자의 반려견이 아니면 등록 불가
        if(user.getId().longValue() != dog.getUser().getId().longValue()) {
            throw new NotYourDogException(ExceptionMessage.NOT_YOUR_DOG);
        }

        // DB에 저장
        Long walkId = walkRepository.save(walk).getWalkId();

        // 전달받은 이미지 저장
        if(walkCreateDto.getImage() != null && !walkCreateDto.getImage().isEmpty()) {

            walk.updateImage(uploadWalkImage(walkId, walkCreateDto.getImage()));
        }

        // 산책 경로 좌표들 저장
        locationRepository.saveAll(walk.getLocation().stream()
                .map(location -> location.setInsertId(walkId)).collect(Collectors.toList()));
    }

    @Override
    public WalkCalendarDto readMyWalks(Long userId, Integer year, Integer month) {

        AtomicReference<Float> distance = new AtomicReference<>(0f);
        AtomicReference<Integer> duration = new AtomicReference<>(0);

        // 요청 월에 대한 기간 설정
        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDateTime start = LocalDateTime.of(year, month,1 ,0,0);
        LocalDateTime end = LocalDateTime.of(year, month, yearMonth.atEndOfMonth().getDayOfMonth(),0,0);

        List<WalkDto> result = walkRepository.findAllByIsDeletedFalseAndCreatedAtBetween(start, end)
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND))
                .stream().map(walk -> {
                    distance.updateAndGet(v -> v + walk.getDistance());
                    duration.updateAndGet(v -> v + walk.getDuration());
                    return WalkMapper.toDto(walk);
                }).collect(Collectors.toList());

        return WalkCalendarDto.builder()
                .walkList(result)
                .totalCnt(result.size())
                .totalDistance(distance.get())
                .totalDuration(duration.get())
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
    public WalkDto updateWalk(WalkUpdateDto walkUpdateDto, Long userId) {

        Walk walk = walkRepository.findById(walkUpdateDto.getWalkId())
                .orElseThrow(()->new WalkNotFoundException(ExceptionMessage.WALK_NOT_FOUND));

        if(userId.longValue() != walk.getUser().getId().longValue()) {
            throw new NotYourWalkException(ExceptionMessage.NOT_YOUR_WALK);
        }

        walk.updateWalk(walkUpdateDto);

        return WalkMapper.toDto(walk);
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
