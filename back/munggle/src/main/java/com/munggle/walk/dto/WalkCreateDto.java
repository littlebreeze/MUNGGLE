package com.munggle.walk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

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

    private Boolean isDeleted;
    private List<LocationCreateDto> location;

    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setImage(MultipartFile file){
        this.image = file;
    }

}
