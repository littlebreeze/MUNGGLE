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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/openapi-db")
@RequiredArgsConstructor
public class OpenAPIDBController {

    private final OpenAPIService openAPIService;

    @Value("${api.serviceKey}")
    private String serviceKey_en;

    // 동물보호관리시스템 유기동물 정보 조회 서비스 URL
    @Value("${api.abandoned}")
    private String abandonedUrl;
    @Value("${api.lostDog}")
    private String lostDogUri;
    @Value("${api.kind}")
    private String kindUri;

    // 상태 변경 시 update 부분 고민

    // 품종 정보 요청 - 관리자만 가능하도록 설정
    @GetMapping("/kinds")
    @ResponseStatus(HttpStatus.OK)
    public void requestKind() throws IOException, ParseException {


        StringBuilder baseUrl = new StringBuilder(abandonedUrl); /*URL*/
        baseUrl.append(kindUri);
        baseUrl.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey_en); /*Service Key*/
        baseUrl.append("&" + URLEncoder.encode("up_kind_cd","UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900) (입력 시 데이터 O, 미입력 시 데이터 X)*/
        baseUrl.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(abandonedUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        String result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(kindUri)
                        .queryParam("serviceKey", serviceKey_en)
                        .queryParam("up_kind_cd", "417000")
                        .queryParam("_type","json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/1543061/abandonmentPublicSrvc")
                        .path(kindUri)
                        .queryParam("serviceKey", serviceKey_en)
                        .queryParam("up_kind_cd", "417000")
                        .queryParam("_type","json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(
                        resultstr -> {
                            // 성공적으로 응답을 받았을 때의 처리
                            System.out.println("Response: " + resultstr);
                        },
                        error -> {
                            // 에러 발생 시의 처리
                            System.err.println("Error: " + error.getMessage());
                        }
                );


//
//        URL url = new URL(baseUrl.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "application/json");
//        conn.setRequestProperty("Content-type", "application/json");
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();

        // DB에 저장
        openAPIService.insertKind(result);
    }

    // 유기 동물 정보 요청 - 관리자만 가능하도록 설정
    @GetMapping("/lostdogs")
    @ResponseStatus(HttpStatus.OK)
    public void requestLostdog() throws IOException, ParseException {

        Long totalCnt = -1L;
        Long pageNo = 0L;
        int numOfRows = 1000;

        long beforeTime = System.currentTimeMillis();
        do {
            // 페이지 번호를 증가시키며 전체 데이터 DB에 저장
            pageNo++;

            StringBuilder baseUrl = new StringBuilder(abandonedUrl); /*URL*/
            baseUrl.append(lostDogUri);
            baseUrl.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey_en); /*Service Key*/
            baseUrl.append("&" + URLEncoder.encode("upkind", "UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900)*/
            baseUrl.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo); /*페이지 번호 (기본값 : 1)*/
            baseUrl.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows); /*페이지당 보여줄 개수 (1,000 이하), 기본값 : 10*/
            baseUrl.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/

            URL url = new URL(baseUrl.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // 중간에 totalCnt가 바뀔수도 있으니까 그때마다 갱신되어도 ok
            totalCnt = openAPIService.insertLostDog(sb.toString());
            System.out.println("totalCnt : " + totalCnt);

            // totalCount에 따라서 요청을 반복
        } while (numOfRows * pageNo < totalCnt);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
        System.out.println("시간차이(m) : "+secDiffTime);
    }

}