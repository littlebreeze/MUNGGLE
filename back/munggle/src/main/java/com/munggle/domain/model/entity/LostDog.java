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

    @Column(length = 100, nullable = false)
    private String thumbImage;  // 썸네일 이미지

    @Column(length = 100, nullable = false)
    private String image;       // 이미지

    @Column(length = 8, nullable = false)
    private String happenDate;  // 접수일

    @Column(length = 100, nullable = false)
    private String happenPlace; // 발견 장소

    @Column(length = 50, nullable = false)
    private String kind;    // 품종

    @Column(length = 30, nullable = false)
    private String color;   // 색상

    @Column(length = 30, nullable = false)
    private String age;     // 나이

    @Column(length = 30, nullable = false)
    private String weight;  // 체중

    @Column(length = 30, nullable = false)
    private String noticeNo;    // 공고번호

    @Column(length = 8, nullable = false)
    private String noticeStartDate; // 공고시작일

    @Column(length = 8, nullable = false)
    private String noticeEndDate; // 공고종료일

    @Column(length = 10, nullable = false)
    private String processState;    // 상태 (공고중, 보호중 ...)

    @Column(length = 1, nullable = false)
    private String sex; // 성별

    @Column(length = 1, nullable = false)
    private String neuterYn;    // 중성화 여부

    @Column(length = 200, nullable = false)
    private String specialMark; // 특징

    @Column(length = 50, nullable = false)
    private String careName;    // 보호소 이름

    @Column(length = 14, nullable = false)
    private String careTel;     // 보호소 전화번호

    @Column(length = 200, nullable = false)
    private String careAddr;    // 보호소 주소

    @Column(length = 50, nullable = false)
    private String orgName;     // 관할 기관

    @Column(length = 20, nullable = false)
    private String chargeName;  // 담당자

    @Column(length = 14, nullable = false)
    private String officeTel;   // 담당자 연락처

    @Column(length = 200)
    private String noticeComment;   // 특이사항

    @Builder
    public LostDog(Long lost_dog_no, String thumbImage, String image, String happenDate, String happenPlace, String kind, String color, String age, String weight, String noticeNo, String noticeStartDate, String noticeEndDate, String processState, String sex, String neuterYn, String specialMark, String careName, String careTel, String careAddr, String orgName, String chargeName, String officeTel, String noticeComment) {
        this.lost_dog_no = lost_dog_no;
        this.thumbImage = thumbImage;
        this.image = image;
        this.happenDate = happenDate;
        this.happenPlace = happenPlace;
        this.kind = kind;
        this.color = color;
        this.age = age;
        this.weight = weight;
        this.noticeNo = noticeNo;
        this.noticeStartDate = noticeStartDate;
        this.noticeEndDate = noticeEndDate;
        this.processState = processState;
        this.sex = sex;
        this.neuterYn = neuterYn;
        this.specialMark = specialMark;
        this.careName = careName;
        this.careTel = careTel;
        this.careAddr = careAddr;
        this.orgName = orgName;
        this.chargeName = chargeName;
        this.officeTel = officeTel;
        this.noticeComment = noticeComment;
    }
}
