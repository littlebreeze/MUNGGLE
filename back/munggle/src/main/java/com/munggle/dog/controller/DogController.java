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
    public Long createDog(@AuthenticationPrincipal User principal,
                          @RequestBody @Valid DogCreateDto dogCreateDto){

        dogCreateDto.setUserId(principal.getId());

        return dogService.insertDog(dogCreateDto);
    }

    @PutMapping("/{dogId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDog(@AuthenticationPrincipal User principal,
                          @PathVariable Long dogId,
                          @RequestBody @Valid DogUpdateDto dogUpdateDto){

        dogService.updateDog(principal.getId(), dogId, dogUpdateDto);
    }

    @PutMapping("/{dogId}/image")
    @ResponseStatus(HttpStatus.OK)
    public void updateImage(@AuthenticationPrincipal User principal,
                          @PathVariable Long dogId,
                          @RequestPart(value = "file", required = false) MultipartFile file){

        dogService.updateDogImage(principal.getId(), dogId, file);
    }

    @DeleteMapping("/{dogId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDog(@AuthenticationPrincipal User principal,
                          @PathVariable Long dogId){
        dogService.deleteDog(principal.getId(), dogId);
    }

    // 반려견 상세 - 반려견 수정 시 필요
    @GetMapping("/{dogId}")
    @ResponseStatus(HttpStatus.OK)
    public DogDetailDto getDetailDog(@PathVariable Long dogId) {
        return dogService.getDetailDog(dogId);
    }

    // 사용자 반려견 리스트
    @GetMapping("/list/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DogDetailDto> getUserDogs(@PathVariable Long userId){
        return dogService.getDogList(userId);
    }
}
