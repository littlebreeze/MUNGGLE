package com.munggle.walk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.munggle.domain.model.entity.Location;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class LocationDto {

    private Long walkId;
    private Float lat;
    private Float lng;
    private Long orderNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    public static Location toEntity(LocationDto locationDto){
        // LocationDto 리스트를 Entity로 변환하기 위한 메서드
        return Location.builder()
                .lat(locationDto.getLat())
                .lng(locationDto.getLng())
                .createdAt(locationDto.getCreatedAt())
                .orderNo(locationDto.getOrderNo())
                .build();
    }
}
