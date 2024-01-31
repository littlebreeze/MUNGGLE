package com.munggle.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_recent_tags")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserRecentTag {

    @Id
    @Column(name = "user_recent_tag_id")
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "tag_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;
}
