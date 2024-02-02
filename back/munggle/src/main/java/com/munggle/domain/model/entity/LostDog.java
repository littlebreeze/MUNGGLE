package com.munggle.domain.model.entity;

import com.munggle.openAPI.dto.LostDogDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "lost_dogs")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LostDog {

    @Id
    @Column(nullable = false)
    private Long lostDogId;  // 유기 번호

    @Size(max = 100)
    @NotNull
    @NotBlank
    private String thumbImage;  // 썸네일 이미지

    @Size(max = 100)
    @NotNull
    @NotBlank
    private String image;       // 이미지

    @Size(max = 8)
    @NotNull
    @NotBlank
    private String happenDate;  // 접수일

    @Size(max = 100)
    @NotNull
    @NotBlank
    private String happenPlace; // 발견 장소

    @Size(max = 50)
    @NotNull
    @NotBlank
    private String kind;    // 품종

    @Size(max = 30)
    @NotNull
    @NotBlank
    private String color;   // 색상

    @Size(max = 30)
    @NotNull
    @NotBlank
    private String age;     // 나이

    @Size(max = 30)
    @NotNull
    @NotBlank
    private String weight;  // 체중

    @Size(max = 30)
    @NotNull
    @NotBlank
    private String noticeNo;    // 공고번호

    @Size(max = 8)
    @NotNull
    @NotBlank
    private String noticeStartDate; // 공고시작일

    @Size(max = 8)
    @NotNull
    @NotBlank
    private String noticeEndDate; // 공고종료일

    @Size(max = 10)
    @NotNull
    @NotBlank
    private String processState;    // 상태 (공고중, 보호중 ...)

    @Size(max = 1)
    @NotNull
    @NotBlank
    private String sex; // 성별

    @Size(max = 1)
    @NotNull
    @NotBlank
    private String neuterYn;    // 중성화 여부

    @Size(max = 200)
    @NotNull
    private String specialMark; // 특징

    @Size(max = 50)
    @NotNull
    @NotBlank
    private String careName;    // 보호소 이름

    @Size(max = 14)
    @NotNull
    @NotBlank
    private String careTel;     // 보호소 전화번호

    @Size(max = 200)
    @NotNull
    @NotBlank
    private String careAddr;    // 보호소 주소

    @Size(max = 50)
    @NotNull
    @NotBlank
    private String orgName;     // 관할 기관

    @Size(max = 20)
    @NotNull
    @NotBlank
    private String chargeName;  // 담당자

    @Size(max = 14)
    @NotNull
    @NotBlank
    private String officeTel;   // 담당자 연락처

    @Size(max = 200)
    private String noticeComment;   // 특이사항

    public static LostDogDto toDto(LostDog lostDog){
        return LostDogDto.builder()
                .lostDogId(lostDog.getLostDogId())
                .thumbImage(lostDog.getThumbImage())
                .image(lostDog.getImage())
                .happenDate(lostDog.getHappenDate())
                .happenPlace(lostDog.getHappenPlace())
                .kind(lostDog.getKind())
                .color(lostDog.getColor())
                .age(lostDog.getAge())
                .weight(lostDog.getWeight())
                .noticeNo(lostDog.getNoticeNo())
                .noticeStartDate(lostDog.getNoticeStartDate())
                .noticeEndDate(lostDog.getNoticeEndDate())
                .sex(lostDog.getSex())
                .neuterYn(lostDog.getNeuterYn())
                .specialMark(lostDog.getSpecialMark())
                .careName(lostDog.getCareName())
                .careTel(lostDog.getCareTel())
                .careAddr(lostDog.getCareAddr())
                .orgName(lostDog.getOrgName())
                .chargeName(lostDog.getChargeName())
                .officeTel(lostDog.getOfficeTel())
                .noticeComment(lostDog.getNoticeComment())
                .build();

    }

}
