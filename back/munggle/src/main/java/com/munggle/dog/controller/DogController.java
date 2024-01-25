package com.munggle.dog.controller;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.dto.DogUpdateDto;
import com.munggle.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    // 반려견 등록
    @PostMapping
    public void createDog(@RequestBody DogCreateDto dogCreateDto){

        // 없는 kindId를 넣으면 InvalidDataAccessApiUsageException
        dogService.insertDog(dogCreateDto);
    }

    @PutMapping("/{dogId}")
    public void updateDog(@PathVariable Long dogId, @RequestBody DogUpdateDto dogUpdateDto){

        dogService.updateDog(dogId, dogUpdateDto);
    }

    @DeleteMapping("/{dogId}")
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
    public List<DogDetailDto> getUserDogs(@PathVariable Long userId){
        return dogService.getDogList(userId);
    }
}
