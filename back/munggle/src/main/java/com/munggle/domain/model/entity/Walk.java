package com.munggle.domain.model.entity;

import com.munggle.walk.dto.LocationDto;
import com.munggle.walk.dto.WalkUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "walks")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Walk {

    // 처음에는 유저번호, 산책 반려견만 채워진 채로 객체 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkId;

    private String walkName;

    private Long userId;

    private Long dogId;

    @Size(max = 50)
    private String description;

    private Integer duration;
    private Integer distance;
    private Float rating;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    @OneToMany(mappedBy = "walk", cascade = CascadeType.REMOVE)
    private List<Location> location;

    public void updateWalk(WalkUpdateDto walkUpdateDto){
        this.walkName = walkUpdateDto.getWalkName();
        this.description = walkUpdateDto.getDescription();
        this.rating = walkUpdateDto.getRating();
    }

    public void setDeleted(){
        this.isDeleted = true;
    }

}
