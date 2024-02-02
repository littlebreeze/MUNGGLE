package com.munggle.domain.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarms")
public class Alarm {

    @Id
    @Column(name = "alarm_id")
    private Long id;

    private String alarmType;

    private Long fromUserId;

    private Long toUserId;

    private LocalDateTime createdAt;

    private Boolean isDeleted;

    private Boolean isChecked;

}
