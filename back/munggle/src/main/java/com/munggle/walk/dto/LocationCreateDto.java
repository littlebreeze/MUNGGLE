package com.munggle.walk.dto;

import com.munggle.domain.model.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class LocationCreateDto {

    private Long walkId;
    private Float lat;
    private Float lng;
    private Long orderNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime createdAt;

    public static Location toEntity(LocationCreateDto locationCreateDto){
        // LocationDto 리스트를 Entity로 변환하기 위한 메서드
        return Location.builder()
                .lat(locationCreateDto.getLat())
                .lng(locationCreateDto.getLng())
                .createdAt(locationCreateDto.getCreatedAt())
                .orderNo(locationCreateDto.getOrderNo())
                .build();
    }
}
