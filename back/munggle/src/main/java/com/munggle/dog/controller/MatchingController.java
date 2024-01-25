package com.munggle.dog.controller;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.service.MatchingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dog-match")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    // 본인 반려견 특징 편집
    @PutMapping("/my/{dogId}")
    public void updateMyDog(@PathVariable Long dogId, @RequestBody DogCharDto dogCharDto){
        matchingService.updateMyDogCharacter(dogId, dogCharDto);
    }

    // 상대 반려견 특징 생성
    @PostMapping("/{dogId}")
    public void createMatching(@PathVariable Long dogId, @RequestBody DogCharDto dogCharDto){

    }
    // 상대 반려견 특징 편집
    @PutMapping("/{dogId}")
    public void updateMatching(@PathVariable Long dogId, @RequestBody DogCharDto dogCharDto){

    }
    // 특징에 따른 반려견 리스트
    @GetMapping("/{dogId}")
    public List<DogDetailDto> matchingList(@PathVariable Long dogId){

        return null;
    }
}
