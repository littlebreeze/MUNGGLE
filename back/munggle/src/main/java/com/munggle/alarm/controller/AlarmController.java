package com.munggle.alarm.controller;

import com.munggle.alarm.dto.AlarmListDto;
import com.munggle.alarm.service.AlarmService;
import com.munggle.domain.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AlarmListDto getAlarms(@AuthenticationPrincipal User principal) {
        Long userId = principal.getId();

        return alarmService.getAlarmList(userId);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer countAlarm(@AuthenticationPrincipal User principal) {
        Long userId = principal.getId();

        return  alarmService.countAlarm(userId);
    }

    @DeleteMapping("/{alarmId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAlarm(@AuthenticationPrincipal User principal,
                            @PathVariable Long alarmId) {
        Long userId = principal.getId();

        alarmService.deleteAlarm(alarmId, userId);
    }

}
