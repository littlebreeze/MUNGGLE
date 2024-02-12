package com.munggle.alarm.mapper;

import com.munggle.alarm.dto.AlarmDetailDto;
import com.munggle.domain.model.entity.Alarm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlarmMapper {
    public static AlarmDetailDto toAlarmDetailDto(Alarm alarm) {
        return AlarmDetailDto.builder()
                .alarmId(alarm.getId())
                .alarmType(alarm.getAlarmType().toString())
                .fromUserId(alarm.getFromUser().getId())
                .targetId(alarm.getTargetId())
                .isChecked(alarm.getIsChecked())
                .build();
    }
}
