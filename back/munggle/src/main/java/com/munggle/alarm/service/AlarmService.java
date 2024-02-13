package com.munggle.alarm.service;

import com.munggle.alarm.dto.AlarmListDto;
import com.munggle.domain.model.entity.User;

public interface AlarmService {

    AlarmListDto getAlarmList(Long userId);

    Integer countAlarm(Long userId);

    void insertAlarm(String type, User fromUser, User toUser, Long target);

    void deleteAlarm(Long alarmId);

}
