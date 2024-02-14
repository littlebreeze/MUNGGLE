package com.munggle.walk.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkCalendarDto;
import com.munggle.walk.dto.WalkCreateDto;
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
                           @RequestBody @Valid WalkCreateDto walkDto){

        walkDto.setUserId(principal.getId());
        walkService.createWalk(walkDto);
    }

    @GetMapping("/{year}/{month}")
    public WalkCalendarDto myWalkList(@AuthenticationPrincipal User principal,
                                      @PathVariable("year") Integer year, @PathVariable("month") Integer month){
        return walkService.readMyWalks(principal.getId(), year, month);
    }

    @GetMapping("/list")
    public List<WalkDto> locationWalkList(@RequestParam Float lat, @RequestParam Float lng){

        // 사용자 정보도 넘겨줘야 프로필을 띄울 수 있음
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

    @PostMapping("/{walkId}")
    public void toggleVisibility(@PathVariable Long walkId){
        walkService.toggleVisibility(walkId);
    }
}
