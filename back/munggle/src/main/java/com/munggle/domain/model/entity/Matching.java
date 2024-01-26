package com.munggle.domain.model.entity;

import com.munggle.dog.dto.DogCharDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void updateMatcing(DogCharDto dogCharDto){
        this.characterId = dogCharDto.toCharacterString();
        this.isNeutering = dogCharDto.getIsNeutering();
    }
}
