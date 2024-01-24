package com.munggle.dog.dto;

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
public class DogDto {

    // 품종, 사용자Id, 생년월일, 이름 필수

    private Long id;
    private Long kindId;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`HH:mm:ss")
    private LocalDateTime birthDate;
    private String size;
    private Float weight;
    private String gender;
    private boolean isNeutering;    // 중성화 여부
    private String name;
    private String image;
    private String description;
    // ,로 구분된 특징 (파싱해서 사용)
    // 수정 시 update 방식으로
    private String characterId;
    private boolean isMatching; // 매칭 온오프

    @Override
    public String toString() {
        return "DogDto{" +
                "kindId=" + kindId +
                ", userId=" + userId +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private boolean isDeleted;
}
