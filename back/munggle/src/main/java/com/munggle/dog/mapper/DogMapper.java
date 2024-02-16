package com.munggle.dog.mapper;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Kind;
import com.munggle.domain.model.entity.type.Gender;
import com.munggle.domain.model.entity.type.SizeType;
import com.munggle.user.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class DogMapper {

    // Dto -> Entity (Create할 때)
    public static Dog toEntity(DogCreateDto dogCreateDto, Kind kind){
        Gender gender = Enum.valueOf(Gender.class, dogCreateDto.getGender());

        SizeType size = null;
        if(dogCreateDto.getSize()!=null)
            size = Enum.valueOf(SizeType.class, dogCreateDto.getSize());

        return Dog.builder()
                .kind(kind)
                .birthDate(dogCreateDto.getBirthDate())
                .size(size)
                .weight(dogCreateDto.getWeight())
                .gender(gender)
                .isNeutering(dogCreateDto.getIsNeutering())
                .name(dogCreateDto.getName())
                .description(dogCreateDto.getDescription())
                .build();
    }

    // Entity -> Dto

    public static DogDetailDto toDetailDto(Dog dog) {
        return DogDetailDto.builder()
                .dogId(dog.getDogId())
                .kindId(dog.getKind().getKindId())
                .kindNm(dog.getKind().getKindNm())
                .birthDate(dog.getBirthDate())
                .size(dog.getSize())
                .weight(dog.getWeight())
                .gender(dog.getGender())
                .isNeutering(dog.getIsNeutering())
                .name(dog.getName())
                .image(dog.getImageUrl())
                .description(dog.getDescription())
                .isMatching(dog.getIsMatching())
                .user(UserMapper.toUserProfileDto(dog.getUser()))
                .build();
    }
}
