package com.munggle.domain.model.entity;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogUpdateDto;
import com.munggle.domain.model.entity.type.Gender;
import com.munggle.domain.model.entity.type.SizeType;
import com.munggle.image.dto.FileInfoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "dogs")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dog extends BaseTimeEntity{

    // 품종, 사용자Id, 생년월일, 이름 필수

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "kind_id")
    private Kind kind;
    //private Long kindId;

    @NotNull
    //private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private SizeType size;

    private Float weight;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean isNeutering;    // 중성화 여부

    // ,로 구분된 특징 (파싱해서 사용)
    // 수정 시 update 방식으로
    private String characterId;

    @NotNull
    @Size(max=15)
    private String name;

    private String imageName;
    private String imageUrl;

    @Size(max=100)
    private String description;

    @Column(columnDefinition = "boolean default false")
    private Boolean isMatching; // 매칭 온오프

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public void setUser(User user){
        this.user = user;
    }

    // 반려견 정보 수정
    public void updateDog(DogUpdateDto dogUpdateDto){
        this.birthDate = dogUpdateDto.getBirthDate();
        if(dogUpdateDto.getSize()!=null)
            this.size = Enum.valueOf(SizeType.class, dogUpdateDto.getSize());
        this.gender = Enum.valueOf(Gender.class, dogUpdateDto.getGender());
        this.weight = dogUpdateDto.getWeight();
        this.isNeutering = dogUpdateDto.getIsNeutering();
        this.name = dogUpdateDto.getName();
        this.description = dogUpdateDto.getDescription();
    }

    // 반려견 특성 변경
    public void updateCharacterId(DogCharDto dogCharDto){
        this.characterId = dogCharDto.toCharacterString();
        this.isNeutering = dogCharDto.getIsNeutering();
    }

    // 반려견 이미지 정보 업데이트
    public void updateImage(FileInfoDto fileInfoDto){
        this.imageName = fileInfoDto.getFileName();
        this.imageUrl = fileInfoDto.getFileURL();
    }

    public void deleteDog(){
        this.isDeleted = true;
    }

    public void onMatching(){
        this.isMatching = true;
    }

    public void offMatching(){
        this.isMatching = false;
    }
}
