package com.munggle.openAPI.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LostDogDto {

    private Long lostDogId;  // 유기 번호
    private String thumbImage;  // 썸네일 이미지
    private String image;       // 이미지
    private String happenDate;  // 접수일
    private String happenPlace; // 발견 장소
    private String kind;    // 품종
    private String color;   // 색상
    private String age;     // 나이
    private String weight;  // 체중
    private String noticeNo;    // 공고번호
    private String noticeStartDate; // 공고시작일
    private String noticeEndDate; // 공고종료일
    private String processState;    // 상태 (공고중, 보호중 ...)
    private String sex; // 성별
    private String neuterYn;    // 중성화 여부
    private String specialMark; // 특징
    private String careName;    // 보호소 이름
    private String careTel;     // 보호소 전화번호
    private String careAddr;    // 보호소 주소
    private String orgName;     // 관할 기관
    private String chargeName;  // 담당자
    private String officeTel;   // 담당자 연락처
    private String noticeComment;   // 특이사항

}
