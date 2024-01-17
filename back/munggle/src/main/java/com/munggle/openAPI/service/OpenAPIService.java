package com.munggle.openAPI.service;

import org.json.simple.parser.ParseException;

public interface OpenAPIService {

    // 품종 정보 저장
    void insertKind(String kindJSON) throws ParseException;

    // 유기 동물 정보 저장
    Long insertLostDog(String lostJSON) throws ParseException ;
}
