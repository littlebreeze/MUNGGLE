package com.munggle.domain.model.entity;

import com.munggle.dog.dto.DogCharDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matchings")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;

    private String characterId;

    private Boolean isNeutering;

    /*
    * 전달된 Dto로 entity 업데이트
    * */
    public void updateMatcing(DogCharDto dogCharDto){
        this.characterId = dogCharDto.toCharacterString();
        this.isNeutering = dogCharDto.getIsNeutering();
    }

    /*
    *  특성 리스트 목록으로 변환
    * */
    public List<String> returnCharacterList(){
        List<String> list = new ArrayList<>();
        for(String str : this.characterId.split(",")){
            list.add(str);
        }

        return list;
    }
}
