package com.munggle.domain.model.entity;

import com.munggle.domain.model.entity.type.AlarmType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter @Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alarms")
@EntityListeners(AuditingEntityListener.class)
public class Alarm {

    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Long targetId;

    @CreatedDate
    private LocalDateTime createdAt;

    private Boolean isChecked;

    public void markAsChecked() {
        this.isChecked = true;
    }
}
