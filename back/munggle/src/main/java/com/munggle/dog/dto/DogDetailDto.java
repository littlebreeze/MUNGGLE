package com.munggle.dog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.munggle.domain.model.entity.type.Gender;
import com.munggle.domain.model.entity.type.SizeType;
import com.munggle.user.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogDetailDto {

    private Long dogId;
    private Long kindId;
    private String kindNm;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime birthDate;
    private SizeType size;
    private Float weight;
    private Gender gender;
    private Boolean isNeutering;    // 중성화 여부
    private String name;
    private String image;   // Url
    private String description;

    private UserProfileDto user;
}
