package com.munggle.dog.controller;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.service.MatchingService;
import com.munggle.domain.model.entity.Matching;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dog-match")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    // 본인 반려견 특징 편집
    @PutMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public void updateMyDog(@RequestBody DogCharDto dogCharDto){
        matchingService.updateMyDogCharacter(dogCharDto);
    }

    // 상대 반려견 특징 생성 - 매칭 옵션 켜기
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createMatching(@RequestBody DogCharDto dogCharDto){

        matchingService.insertMatchingCharacter(dogCharDto);
    }

    // 상대 반려견 특징 편집
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateMatching(@RequestBody DogCharDto dogCharDto){

        matchingService.updateMatchingCharacter(dogCharDto);
    }

    // 특징에 따른 반려견 리스트
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<DogDetailDto> matchingList(@RequestBody Long dogId){

        return matchingService.matchingList(dogId);
    }

    // 내 특성 - 수정 시 필요
    @GetMapping ("/my")
    @ResponseStatus(HttpStatus.OK)
    public DogCharDto myCharacter(@RequestBody Long dogId){

        return matchingService.myCharacterList(dogId);
    }

    // 상대 특성 - 수정 시 필요
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DogCharDto matchingCharacter(@RequestBody Long dogId){

        return matchingService.matchingCharaterList(dogId);
    }

    // 매칭 온오프
    @PostMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public void toggleMatching(@RequestBody Long dogId){

        matchingService.toggleMatching(dogId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveMatchingSelection(@RequestBody ){


    }

}
