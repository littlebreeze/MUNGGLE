package com.munggle.dog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DogCreateDto {

    // 품종, 사용자Id, 생년월일, 이름 필수

    private Long kindId;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime birthDate;
    private String size;
    private Float weight;
    private String gender;
    @JsonProperty("isNeutering")
    private boolean isNeutering;    // 중성화 여부
    private String name;
    private String image;
    private String description;
}
