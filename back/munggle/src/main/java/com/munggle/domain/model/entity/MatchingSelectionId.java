package com.munggle.domain.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MatchingSelectionId implements Serializable {

    private Long dogId;
    private Long otherId;
}
