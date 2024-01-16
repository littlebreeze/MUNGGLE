package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LostDog {

    @Id
    @Column(nullable = false)
    private Long lost_dog_no;  // 유기 번호

    @Column(length = 20, nullable = false)
    private String location;    // 보호소 이름, 보호 장소

    @Column(length = 20, nullable = false)
    private String kind;    // 품종

    @Column(nullable = false)
    private Integer age;    // 나이

    @Column(length = 50, nullable = false)
    private String image;   // 이미지

    @Column(length = 50, nullable = false)
    private String thumb_image;   // 썸네일 이미지

    @Column(length = 20, nullable = false)
    private String special_feature; // 특징

    // 그 외 : 발견 장소, 색상, 공고 종료(시작)일, 성별, 공고번호, 특이사항

    @Builder
    public LostDog(Long lost_dog_no, String location, String kind, Integer age, String image, String thumb_image, String special_feature) {
        this.lost_dog_no = lost_dog_no;
        this.location = location;
        this.kind = kind;
        this.age = age;
        this.image = image;
        this.thumb_image = thumb_image;
        this.special_feature = special_feature;
    }
}
