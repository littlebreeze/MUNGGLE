package com.munggle.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "tags")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter @Builder
public class Tag {

    @Id
    private Long tagId;

    @NotNull
    @NotBlank
    private String tagNm;
}
