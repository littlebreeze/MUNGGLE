package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matching_selections")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchingSelection {

    @EmbeddedId
    MatchingSelectionId id;

    @OneToOne
    @MapsId("dogId")
    @JoinColumn(name = "dog_id")
    private Dog dog;

    @OneToOne
    @MapsId("otherId")
    @JoinColumn(name = "other_id")
    private Dog other;
}
