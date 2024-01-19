package com.munggle.walk.controller;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.service.WalkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/walk")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    @GetMapping("/test")
    public String test(){
        walkService.deleteWalk(1L);
        return "테스트인디유";
    }
    @PostMapping("/create")
    public void createWalk(@RequestBody @Valid Walk walk){
        //System.out.println(walk.getWalkName()+walk.getWalkId()+walk.getDogId()+walk.getDescription());
        walkService.createWalk(walk);
    }
}
