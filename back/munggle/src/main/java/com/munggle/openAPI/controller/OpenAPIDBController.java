package com.munggle.openAPI.controller;

import com.munggle.domain.model.entity.Kind;
import com.munggle.domain.model.entity.LostDog;
import com.munggle.domain.model.entity.User;
import com.munggle.openAPI.dto.KindDto;
import com.munggle.openAPI.dto.LostDogDto;
import com.munggle.openAPI.service.OpenAPIService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/openapi-db")
@RequiredArgsConstructor
public class OpenAPIDBController {

    private final OpenAPIService openAPIService;

    @Value("${api.serviceKey}")
    private String serviceKey_en;
    @Value("${api.serviceKey_decode}")
    private String serviceKey_de;

    // 동물보호관리시스템 유기동물 정보 조회 서비스 URL
    @Value("${api.abandoned}")
    private String abandonedUrl;
    @Value("${api.lostDog}")
    private String lostDogUri;
    @Value("${api.kind}")
    private String kindUri;

    // 품종 정보 요청 - 관리자만 가능하도록 설정
    @GetMapping("/kinds")
    @ResponseStatus(HttpStatus.OK)
    public void requestKind() throws IOException, ParseException, ParserConfigurationException, SAXException {

        WebClient webClient = WebClient.builder()
                .baseUrl(abandonedUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(kindUri)
                        .queryParam("serviceKey", serviceKey_de)
                        .queryParam("up_kind_cd", "417000")
                        .queryParam("_type","json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // DB에 저장
        openAPIService.insertKind(result);
    }

    // 유기 동물 정보 요청 - 관리자만 가능하도록 설정
    @GetMapping("/lostdogs")
    @ResponseStatus(HttpStatus.OK)
    public void requestLostdog() throws IOException, ParseException, ParserConfigurationException, SAXException {

        Long totalCnt = -1L;
        Long pageNo = 0L;
        int numOfRows = 1000;

        long beforeTime = System.currentTimeMillis();
        do {
            // 페이지 번호를 증가시키며 전체 데이터 DB에 저장
            pageNo++;

            WebClient webClient = WebClient.builder()
                    .baseUrl(abandonedUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            Long finalPageNo = pageNo;
            String result = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(lostDogUri)
                            .queryParam("serviceKey", serviceKey_de)
                            .queryParam("upkind", "417000")
                            .queryParam("pageNo", finalPageNo)
                            .queryParam("numOfRows",numOfRows)
                            .queryParam("_type","json")
                            .build())
                    .retrieve()
                    // DataBufferLimitException
                    .bodyToFlux(DataBuffer.class)
                    .map(dataBuffer -> {
                        // DataBuffer를 문자열로 변환
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        DataBufferUtils.release(dataBuffer);
                        return new String(bytes, StandardCharsets.UTF_8);
                    })
                    .collectList()
                    .map(list -> String.join("", list)) // 여러 데이터 버퍼를 하나의 문자열로 합침
                    .block();


            // 중간에 totalCnt가 바뀔수도 있으니까 그때마다 갱신되어도 ok
            totalCnt = openAPIService.insertLostDog(result);
            System.out.println("totalCnt : " + totalCnt);

            // totalCount에 따라서 요청을 반복
        } while (numOfRows * pageNo < totalCnt);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

}