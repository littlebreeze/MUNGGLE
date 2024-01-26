package com.munggle.walk.dto;

import com.munggle.domain.model.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
    private boolean isDeleted;
    private List<Location> locationDtoList;

    // LocationDto를 Location으로 바꾸는 함수
//    public List<Location> toEntity(){
//        List<Location> locationList = new ArrayList<>();
//
//        for(LocationDto locationDto : this.locationDtoList){
//            locationList.add(Location.builder().walk(locationDto.g))
//        }
//
//        return locationList;
//    }
}
