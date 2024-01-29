package com.munggle.dog.controller;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.dto.DogUpdateDto;
import com.munggle.dog.service.DogService;
import com.munggle.domain.model.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    // 반려견 등록
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createDog(@AuthenticationPrincipal User principal,
                          @RequestPart(value = "dto") @Valid DogCreateDto dogCreateDto,
                          @RequestPart(value = "file", required = false) MultipartFile file){

        dogCreateDto.setUserId(11L);//(principal.getId());
        dogCreateDto.setImage(file);

        // 없는 kindId를 넣으면 InvalidDataAccessApiUsageException
        dogService.insertDog(dogCreateDto);
    }

    @PutMapping("/{dogId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDog(@PathVariable Long dogId, @RequestBody DogUpdateDto dogUpdateDto,
                          @RequestPart(value = "file", required = false) MultipartFile file){

        dogService.updateDog(dogId, dogUpdateDto);
    }

    @DeleteMapping("/{dogId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDog(@PathVariable Long dogId){
        dogService.deleteDog(dogId);
    }

    // 반려견 상세
//    @GetMapping("/{dogId}")
//    public DogDetailDto getDetailDog(@PathVariable Long dogId) {
//        return dogService.getDetailDog(dogId);
//    }

    // 사용자 반려견 리스트
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DogDetailDto> getUserDogs(@PathVariable Long userId){
        return dogService.getDogList(userId);
    }
}
