package com.munggle.dog.controller;

import com.munggle.dog.dto.DogDto;
import com.munggle.dog.service.DogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    // 반려견 등록
    @PostMapping
    public void createDog(@RequestBody DogDto dogDto){

        // 없는 kindId를 넣으면 InvalidDataAccessApiUsageException
        dogService.insertDog(dogDto);
    }
}
