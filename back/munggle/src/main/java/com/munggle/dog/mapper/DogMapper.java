package com.munggle.dog.mapper;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Kind;
import org.springframework.stereotype.Component;

@Component
public class DogMapper {

    // Dto -> Entity (Create할 때)
    public static Dog toEntity(DogCreateDto dogCreateDto){
        return Dog.builder()
                .kind(Kind.builder().kindId(dogCreateDto.getKindId()).kindNm("").build())
                //.userId(dogCreateDto.getUserId())
                .birthDate(dogCreateDto.getBirthDate())
                .size(dogCreateDto.getSize())
                .weight(dogCreateDto.getWeight())
                .gender(dogCreateDto.getGender())
                .isNeutering(dogCreateDto.getIsNeutering())
                .name(dogCreateDto.getName())
                //.image(dogCreateDto.getImage())
                .description(dogCreateDto.getDescription())
                .build();
    }

    // Entity -> Dto

    public static DogDetailDto toDetailDto(Dog dog) {

        return DogDetailDto.builder()
                .kindId(dog.getKind().getKindId())
                .birthDate(dog.getBirthDate())
                .size(dog.getSize())
                .weight(dog.getWeight())
                .gender(dog.getGender())
                .isNeutering(dog.getIsNeutering())
                .name(dog.getName())
                .image(dog.getImageUrl())
                .description(dog.getDescription())
                .build();
    }
}
