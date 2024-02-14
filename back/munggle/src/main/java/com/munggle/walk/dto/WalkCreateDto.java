package com.munggle.walk.dto;

import com.munggle.domain.model.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class WalkCreateDto {

    private String walkName;
    private Long userId;
    private Long dogId;
    private Integer duration;
    private Integer distance;
    private Float rating;
    private String description;

    private MultipartFile image;

    private boolean isDeleted;
    private List<LocationDto> location;

    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setImage(MultipartFile file){
        this.image = file;
    }

}
