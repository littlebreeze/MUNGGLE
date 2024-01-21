package com.munggle.walk.dto;

import com.munggle.domain.model.entity.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalkDto {

    private Long walkId;

    private String walkName;

    private Long userId;

    private Long dogId;

    private String description;

    private Integer duration;
    private Integer distance;
    private Float rating;

    private boolean isDeleted;

    private List<LocationDto> location;

}
