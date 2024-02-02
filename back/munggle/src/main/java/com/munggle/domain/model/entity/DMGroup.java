package com.munggle.domain.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dm_groups")
public class DMGroup extends BaseTimeEntity{
    @Id
    @Column(name = "dm_id")
    private Long id;

    private Long startUserId;

    private Long partiUserId;

    private Boolean isDeleted;
}
