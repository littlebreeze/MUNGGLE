package com.munggle.walk.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkDto;
import com.munggle.walk.dto.WalkUpdateDto;
import com.munggle.walk.service.WalkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/walks")
@RequiredArgsConstructor
public class WalkController {

    private final WalkService walkService;

    @PostMapping
    public void createWalk(@AuthenticationPrincipal User principal,
                           @RequestBody @Valid WalkDto walkDto){

        walkDto.setUserId(principal.getId());
        walkService.createWalk(walkDto);
    }

    @GetMapping
    public List<WalkDto> myWalkList(@AuthenticationPrincipal User principal){
        return walkService.readMyWalks(principal.getId());
    }

    @GetMapping("/list")
    public List<WalkDto> locationWalkList(@RequestParam Float lat, @RequestParam Float lng){

        return walkService.readLocationWalks(lat, lng);
    }

    @GetMapping("/{walkId}")
    public WalkDto walkDetail(@PathVariable Long walkId){
        return walkService.detailWalk(walkId);
    }

    @PutMapping("/{walkId}")
    public void walkUpdate(@PathVariable Long walkId, @RequestBody WalkUpdateDto walkUpdateDto){
        walkService.updateWalk(walkUpdateDto);
    }

    @DeleteMapping("/{walkId}")
    public void walkDelete(@PathVariable Long walkId){
        walkService.deleteWalk(walkId);
    }
}
