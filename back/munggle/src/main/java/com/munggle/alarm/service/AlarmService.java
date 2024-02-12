package com.munggle.alarm.service;

import com.munggle.alarm.dto.AlarmCreateDto;
import com.munggle.alarm.dto.AlarmListDto;

public interface AlarmService {

    void InsetAlarm(AlarmCreateDto alarmCreateDto);

    AlarmListDto getAlarmList(Long userId);

    void deleteAlarm(Long alarmId);

    void checkAlarms(Long userId);
}
