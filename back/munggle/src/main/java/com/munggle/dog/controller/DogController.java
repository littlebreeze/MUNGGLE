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

        dogCreateDto.setUserId(principal.getId());
        dogCreateDto.setImage(file);

        // 없는 kindId를 넣으면 InvalidDataAccessApiUsageException
        dogService.insertDog(dogCreateDto);
    }

    @PutMapping("/{dogId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDog(@AuthenticationPrincipal User principal,
                          @PathVariable Long dogId,
                          @RequestPart(value = "dto") @Valid DogUpdateDto dogUpdateDto,
                          @RequestPart(value = "file", required = false) MultipartFile file){

        dogUpdateDto.setImage(file);
        dogService.updateDog(principal.getId(), dogId, dogUpdateDto);
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

}
