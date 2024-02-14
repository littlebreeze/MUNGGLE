package com.munggle.walk.dto;

import com.munggle.domain.model.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalkCreateDto {

    private String walkName;
    private Long userId;
    private Long dogId;
    private Integer duration;
    private Integer distance;
    private Float rating;
    private String description;

    private MultipartFile image;

    private Boolean isDeleted;
    private List<LocationDto> location;

    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setImage(MultipartFile file){
        this.image = file;
    }
}
