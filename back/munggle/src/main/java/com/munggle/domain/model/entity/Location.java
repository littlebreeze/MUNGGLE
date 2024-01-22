package com.munggle.domain.model.entity;

import com.munggle.walk.dto.LocationDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @NotNull
    private Float lat;

    @NotNull
    private Float lng;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;   // String -> 날짜로 넣을 것임

    // 외래키 컬럼이 자동 생성되므로 따로 적어주지 않아도 된다.
    @ManyToOne
    @JoinColumn(name = "walk_id")
    private Walk walk;

    public Location setInsertId(Long walkId){

        // 외래키 설정을 위해 Walk에 Id값 세팅
        Walk walk = Walk.builder().walkId(walkId).build();
        return Location.builder()
                .walk(walk)
                .lat(this.lat)
                .lng(this.lng)
                .createdAt(this.createdAt)
                .build();
    }
}
