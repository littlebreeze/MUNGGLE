package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Kind {

    @Id
    @Column(nullable = false)
    private Long kindNo;   // 품종 코드

    @Column(length = 50, nullable = false)
    private String kindNm; // 품종 명

    @Builder
    public Kind(Long kind_no, String kind_nm) {
        this.kindNo = kind_no;
        this.kindNm = kind_nm;
    }
}
