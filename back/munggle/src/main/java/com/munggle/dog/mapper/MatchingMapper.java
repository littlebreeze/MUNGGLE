package com.munggle.dog.mapper;

import com.munggle.dog.dto.CharacterDto;
import com.munggle.domain.model.entity.Character;

public class MatchingMapper {

    public static CharacterDto toCharDto(Character character){
        return CharacterDto.builder()
                .characterName(character.getCharacterNm())
                .build();
    }
}
