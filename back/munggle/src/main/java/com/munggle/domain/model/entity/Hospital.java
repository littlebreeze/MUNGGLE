package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital {

    @Id
    @Column(nullable = false)
    private Long hospital_no;

    @Column(length = 20, nullable = false)
    private String name;    // 시설명

    @Column(length = 30, nullable = false)
    private String location;    // 도로명 주소 (지번 주소)

    @Column(length = 20, nullable = false)
    private String run_time;    // 운영 시간

    //@NotNull
    @Column(length = 10, nullable = false)
    private String phone_number;    // 전화번호

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    // 그 외 : 위도, 경도, 홈페이지, 휴무일
    // 카테고리 3에 병원인지 표시 (기본정보 장소설명)

    @Builder
    public Hospital(Long hospital_no, String name, String location, String run_time, String phone_number, Double latitude, Double longitude) {
        this.hospital_no = hospital_no;
        this.name = name;
        this.location = location;
        this.run_time = run_time;
        this.phone_number = phone_number;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
