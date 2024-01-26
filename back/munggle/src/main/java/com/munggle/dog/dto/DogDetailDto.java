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
public class DogDetailDto {

    private Long kindId;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime birthDate;
    private String size;
    private Float weight;
    private String gender;
    private Boolean isNeutering;    // 중성화 여부
    private String name;
    private String image;
    private String description;
}
