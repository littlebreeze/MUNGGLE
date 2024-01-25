package com.munggle.domain.model.entity;

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
    private Dog dog;

    private String characterId;

    private boolean isNeutering;
}
