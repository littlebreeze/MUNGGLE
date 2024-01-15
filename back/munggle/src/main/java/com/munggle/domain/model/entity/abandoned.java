package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class abandoned {

    @Id
    @Column(nullable = false)
    private Long abandoned_no;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String location;

    @Column(length = 20, nullable = false)
    private String kind;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 20, nullable = false)
    private String image;

    @Column(length = 20, nullable = false)
    private String special_feature;

    @Builder
    public abandoned(Long abandoned_no, String name, String location, String kind, Integer age, String image, String special_feature) {
        this.abandoned_no = abandoned_no;
        this.name = name;
        this.location = location;
        this.kind = kind;
        this.age = age;
        this.image = image;
        this.special_feature = special_feature;
    }
}
