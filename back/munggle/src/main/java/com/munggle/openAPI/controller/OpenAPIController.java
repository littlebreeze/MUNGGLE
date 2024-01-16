package com.munggle.openAPI.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/openapi")
public class OpenAPIController {

    private final static String serviceKey_en = "B1L1Rw7jMLZhmTHNY5NTrSAncrKqk%2BskTwN41NpjuvO6jVQXKLoxfFaeIAGYqBsiDit4V9OMdG6DqGtt2gOBHQ%3D%3D"; //  요청 서비스키 (Encoding)
    private final static String serviceKey_de = "B1L1Rw7jMLZhmTHNY5NTrSAncrKqk+skTwN41NpjuvO6jVQXKLoxfFaeIAGYqBsiDit4V9OMdG6DqGtt2gOBHQ==";  // 요청 서비스키 (Decoding)

    // 동물보호관리시스템 유기동물 정보 조회 서비스 URL
    private String lostDogUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc";
    private String abandonedUri = "/abandonmentPublic"; // 유기 동물 정보
    private String kindUri = "/kind";   // 품종 정보

    // 전국 반려동물 동반 가능 문화시설 위치 데이터 URL
    private String hospitalUrl = "https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc";

    // 품종 정보 요청
    @GetMapping("/kind")
    public String kind() throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/kind"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey_en); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("up_kind_cd","UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900) (입력 시 데이터 O, 미입력 시 데이터 X)*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
        JSONObject response = (JSONObject)jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");
        JSONArray item = (JSONArray) items.get("item");
        System.out.println(item.get(0).toString());
        System.out.println("이지원한글꺠지나?");

        return item.get(0).toString();
    }

    // 유기 동물 정보 요청
    // 시도, 시군구, 보호소, 품종 까지 다 필요하기 때문에 다른 상세기능 정보도 디비에 넣어야할...것 같다
    @GetMapping("/lostdog")
    public String lostDog() throws IOException{
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey_en); /*Service Key*/
        //urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*유기날짜(검색 시작일) (YYYYMMDD)*/
        //urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*유기날짜(검색 종료일) (YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900)*/
        //urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조)*/
        //urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조)*/
        //urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조)*/
        //urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조)*/
        //urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*상태(전체 : null(빈값), 공고중 : notice, 보호중 : protect)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호 (기본값 : 1)*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*페이지당 보여줄 개수 (1,000 이하), 기본값 : 10*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

        return sb.toString();
    }

    // 병원 정보 요청
    @GetMapping("/hospital")
    public String hospital(){

        String result = "";

        StringBuilder urlBuilder = new StringBuilder(hospitalUrl);
        urlBuilder.append("?").append("page=1");
        urlBuilder.append("&").append("perPage=10");
        urlBuilder.append("&").append("serviceKey=").append(serviceKey_en);
        //"?page=1&perPage=10&serviceKey=B1L1Rw7jMLZhmTHNY5NTrSAncrKqk%2BskTwN41NpjuvO6jVQXKLoxfFaeIAGYqBsiDit4V9OMdG6DqGtt2gOBHQ%3D%3D";

        // open API로 요청은 가능한데
        // 지역 필터링...이 따로 없어서
        // 그냥 파일 데이터를 DB에 넣고
        // 중간에 업데이트...하는 방식이어야할지도
        try {
            URL url = new URL(urlBuilder.toString());
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
