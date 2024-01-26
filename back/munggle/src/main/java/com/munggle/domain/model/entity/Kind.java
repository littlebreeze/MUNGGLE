package com.munggle.domain.model.entity;

import com.munggle.openAPI.dto.KindDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "kinds")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Kind {

    @Id
    @NotNull
    private Long kindId;   // 품종 코드

    @Size(max = 50)
    @NotNull
    @NotBlank
    private String kindNm; // 품종 명

    public static KindDto toDto(Kind kind){
        return KindDto.builder()
                .kindId(kind.getKindId())
                .kindNm(kind.getKindNm())
                .build();
    }

}
