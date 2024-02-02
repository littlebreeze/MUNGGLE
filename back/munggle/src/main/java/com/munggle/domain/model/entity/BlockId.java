package com.munggle.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BlockId implements Serializable {

    @Column(name = "block_from_id")
    private Long blockFromId;

    @Column(name = "block_to_id")
    private Long blockToId;
}
