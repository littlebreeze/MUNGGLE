package com.munggle.dog.service;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.dto.DogUpdateDto;
import com.munggle.dog.mapper.DogMapper;
import com.munggle.dog.repository.DogRepository;
import com.munggle.domain.exception.DogNotFoundException;
import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.User;
import com.munggle.image.dto.FileInfoDto;
import com.munggle.image.service.FileS3UploadService;
import com.munggle.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final FileS3UploadService fileS3UploadService;

    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    // 전달된 MultipartFile 이미지 업로드 경로 : dog/반려견ID/

    String dogFilePath = "dog/";

    public FileInfoDto uploadDogImage(Long dogId, MultipartFile file){
        // 이미지 정보 저장
        String uploadPath = dogFilePath + dogId + "/";
        FileInfoDto fileInfoDto = fileS3UploadService.uploadFile(uploadPath, file);

        return fileInfoDto;
    }

    @Override
    @Transactional
    public void insertDog(DogCreateDto dogCreateDto) {
        Dog dog = DogMapper.toEntity(dogCreateDto);

        // Dto로 넘어온 userId로 user 세팅
        User user = userRepository.findByIdAndIsEnabledTrue(dogCreateDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        dog.setUser(user);

        // 나머지 반려견 정보 저장 후
        Long dogId = dogRepository.save(dog).getDogId();

        // 전달된 이미지가 있는 경우에만 수정
        if(dogCreateDto.getImage() != null) {

            dog.updateImage(uploadDogImage(dogId, dogCreateDto.getImage()));
        }
    }

    @Override
    @Transactional
    public void updateDog(Long dogId, DogUpdateDto dogUpdateDto) {
        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        dog.updateDog(dogUpdateDto);

        // 전달된 파일이 있는 경우에 기존 이미지 삭제 후 변경
        if(dogUpdateDto.getImage() != null){

            // 기존 파일 S3에서 삭제
            String uploadPath = dogFilePath + dogId + "/";
            fileS3UploadService.removeFolderFiles(uploadPath);

            dog.updateImage(uploadDogImage(dogId, dogUpdateDto.getImage()));
        }
    }

    @Override
    @Transactional
    public void deleteDog(Long dogId) {
        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        dog.deleteDog();
    }

    @Override
    public DogDetailDto getDetailDog(Long dogId) {
        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        return DogMapper.toDetailDto(dog);
    }

    @Override
    public List<DogDetailDto> getDogList(Long userId) {

        // 넘겨 받은 사용자가 있는지 확인
        User user = userRepository.findByIdAndIsEnabledTrue(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        
        List<Dog> list = dogRepository.findAllByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        return list.stream().map(dog -> DogMapper.toDetailDto(dog)).collect(Collectors.toList());
    }
}
