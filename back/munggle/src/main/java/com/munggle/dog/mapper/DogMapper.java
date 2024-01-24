package com.munggle.dog.mapper;

import com.munggle.dog.dto.DogDto;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Kind;
import org.springframework.stereotype.Component;

@Component
public class DogMapper {

    // Dto -> Entity
    public static Dog toEntity(DogDto dogDto){
        return Dog.builder()
                .kind(Kind.builder().kindId(dogDto.getKindId()).kindNm("").build())
                .userId(dogDto.getUserId())
                .birthDate(dogDto.getBirthDate())
                .size(dogDto.getSize())
                .weight(dogDto.getWeight())
                .gender(dogDto.getGender())
                .isNeutering(dogDto.isNeutering())
                .name(dogDto.getName())
                .image(dogDto.getImage())
                .description(dogDto.getDescription())
                .build();
    }

    // Entity -> Dto
}
