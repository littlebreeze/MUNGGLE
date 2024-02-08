package com.munggle.alarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController {

    @GetMapping("/userId")
    @ResponseStatus(HttpStatus.OK)
    public String getAlarms(@PathVariable Long userId) {
        return null;
    }

}
