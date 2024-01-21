package com.munggle.walk.dto;

import com.munggle.domain.model.entity.Location;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class LocationDto {
    private Long walkId;
    private Float lat;
    private Float lng;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;

    public static Location toEntity(LocationDto locationDto){
        return Location.builder()
                .lat(locationDto.getLat())
                .lng(locationDto.getLng())
                .createdAt(locationDto.getCreateAt())
                .build();
    }
}
