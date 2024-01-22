package com.munggle.walk.controller;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkDto;
import com.munggle.walk.service.WalkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    @PostMapping("/record")
    public void createWalk(@RequestBody @Valid WalkDto walkDto){

        System.out.println(walkDto.getLocation().get(0).getCreatedAt());
        walkService.createWalk(walkDto);
    }

    @GetMapping("/myList/{userId}")
    public List<WalkDto> myWalkList(@PathVariable Long userId){
        return walkService.readMyWalks(userId);
    }

}
