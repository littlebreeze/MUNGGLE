package com.munggle.openAPI.service;

import com.munggle.domain.model.entity.Kind;
import com.munggle.openAPI.repository.KindRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OpenAPIServiceImpl implements OpenAPIService {

    private final KindRepository kindRepository;
    @Override
    public void insertKind(String kindJSON) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(kindJSON);
        JSONObject response = (JSONObject)jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");
        JSONArray item = (JSONArray) items.get("item");

        for(int idx=0; idx<item.size(); idx++){
            JSONObject tmp = (JSONObject) item.get(idx);
            //System.out.println(tmp.toString());
            // tmp.get("knm")
            // tmp.get("kindCd")
            Long code = Long.parseLong(String.valueOf(tmp.get("kindCd")));
            String name = String.valueOf(tmp.get("knm"));
            Kind kind = Kind.builder()
                    .kind_nm(name)
                    .kind_no(code)
                    .build();

            kindRepository.save(kind);
            // Repository로 DB에 저장 코드 작성하기
        }
    }

    @Override
    public void insertLostDog(String lostJSON) {

    }
}
