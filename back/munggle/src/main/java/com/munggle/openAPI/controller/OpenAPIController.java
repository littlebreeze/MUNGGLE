package com.munggle.openAPI.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.openAPI.dto.KindDto;
import com.munggle.openAPI.dto.LostDogDto;
import com.munggle.openAPI.service.OpenAPIService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/openapi")
@RequiredArgsConstructor
public class OpenAPIController {

    private final OpenAPIService openAPIService;

    // DB Select
    // 입력한 값에 따라 품종 리스트
    @GetMapping(value = {"/kinds/{input}", "/kinds", "/kinds/"})
    @ResponseStatus(HttpStatus.OK)
    public List<KindDto> kind(@PathVariable(required = false) Optional<String> input){

        // 입력값이 없을 때는 ""를 넘겨 전체 리스트가 반환되도록 한다.
        return openAPIService.selectKind(input.orElse(""));
    }

    // 보여줄 유기동물 정보
    // 지역, 품종
    @GetMapping("/lostdogs")
    @ResponseStatus(HttpStatus.OK)
    public List<LostDogDto> listOfLostDog(@AuthenticationPrincipal User principal, Optional<String> region, Optional<String> kind){

        String careAddr = region.orElse("");
        String inputKind = kind.orElse("");

        return openAPIService.selectListDog(careAddr,inputKind);
    }
}