package com.munggle.domain.model.entity;

import com.munggle.domain.model.entity.type.AlarmType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter @Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alarms")
public class Alarm {

    @Id
    @Column(name = "alarm_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne @NotNull
    @JoinColumn(name = "to_user_id")
    private User toUser;

    // postId, dmId 값 저장
    private Long targetId;

    private LocalDateTime createdAt;

    private Boolean isChecked;

    public void markAsChecked() {
        this.isChecked = true;
    }
}
