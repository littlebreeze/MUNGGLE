package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "characters")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String characterNm;

    /**
     * ["고집셈", "공격적임", "놀기 좋아함", "독립적임", "말썽꾸러기", "먹보", "모험을 좋아함", "방구석 게으름뱅이", "사냥꾼", "상냥함", "시끄러움", "영리함", "잘 놀람", "충성스러움", "털이 많음", "호기심 많음", "활동적임"]
     * */

    /*
    * INSERT INTO `characters` (`character_nm`) VALUES ('고집셈'), ('공격적임'), ('놀기 좋아함'), ('독립적임'), ('말썽꾸러기'), ('먹보'), ('모험을 좋아함'), ('방구석 게으름뱅이'), ('사냥꾼'), ('상냥함'), ('시끄러움'), ('영리함'), ('잘 놀람'), ('충성스러움'), ('털이 많음'), ('호기심 많음'), ('활동적임');
    * */
}
