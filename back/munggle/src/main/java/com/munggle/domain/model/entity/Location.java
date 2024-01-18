package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Entity
@Table(name = "locations")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private Long locationId;

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotNull
    @CreationTimestamp
    private Date createdAt;

    // 외래키 컬럼이 자동 생성되므로 따로 적어주지 않아도 된다.
    @ManyToOne
    @JoinColumn(name = "walk_id")
    private Walk walk;

}
