package com.munggle.dog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogCharDto {

    private List<String> characterId;
    private Boolean isNeutering;

    public String toCharacterString(){
        StringBuilder sb = new StringBuilder();

        for(String character : this.characterId){
            sb.append(character).append(",");
        }

        // 마지막에 붙은 ,삭제
        return sb.deleteCharAt(sb.toString().length()-1).toString();
    }
}
