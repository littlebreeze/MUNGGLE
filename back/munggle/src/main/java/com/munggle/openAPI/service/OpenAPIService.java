package com.munggle.openAPI.service;

import com.munggle.domain.model.entity.Kind;
import com.munggle.domain.model.entity.LostDog;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface OpenAPIService {

    // 품종 정보 저장
    void insertKind(String kindJSON) throws ParseException;

    // 유기 동물 정보 저장
    Long insertLostDog(String lostJSON) throws ParseException ;

    // 입력에 따른 품종 리스트
    List<Kind> selectKind(String input);

    List<LostDog> selectListDog(String region, String kind);
}
