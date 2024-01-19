package com.munggle.openAPI.service;

import com.munggle.domain.model.entity.Kind;
import com.munggle.domain.model.entity.LostDog;
import com.munggle.openAPI.repository.KindRepository;
import com.munggle.openAPI.repository.LostDogRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAPIServiceImpl implements OpenAPIService {

    private final KindRepository kindRepository;
    private final LostDogRepository lostDogRepository;

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

            Long code = Long.parseLong(String.valueOf(tmp.get("kindCd")));
            String name = String.valueOf(tmp.get("knm"));
            Kind kind = Kind.builder()
                    .kindNm(name)
                    .kindId(code)
                    .build();

            // Repository로 DB에 저장 코드 작성하기
            kindRepository.save(kind);
        }
    }

    @Override
    public Long insertLostDog(String lostJSON) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(lostJSON);
        JSONObject response = (JSONObject)jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        Long totalCount = Long.parseLong(String.valueOf(body.get("totalCount")));
        JSONObject items = (JSONObject) body.get("items");
        JSONArray item = (JSONArray) items.get("item");

        for(int idx=0; idx<item.size(); idx++){
            JSONObject tmp = (JSONObject) item.get(idx);

            LostDog lostDog = LostDog.builder()
                    .lostDogId(Long.parseLong(String.valueOf(tmp.get("desertionNo"))))
                    .thumbImage(String.valueOf(tmp.get("filename")))
                    .image(String.valueOf(tmp.get("popfile")))
                    .happenDate(String.valueOf(tmp.get("happenDt")))
                    .happenPlace(String.valueOf(tmp.get("happenPlace")))
                    .kind(String.valueOf(tmp.get("kindCd")))
                    .color(String.valueOf(tmp.get("colorCd")))
                    .age(String.valueOf(tmp.get("age")))
                    .weight(String.valueOf(tmp.get("weight")))
                    .noticeNo(String.valueOf(tmp.get("noticeNo")))
                    .noticeStartDate(String.valueOf(tmp.get("noticeSdt")))
                    .noticeEndDate(String.valueOf(tmp.get("noticeEdt")))
                    .processState(String.valueOf(tmp.get("processState")))
                    .sex(String.valueOf(tmp.get("sexCd")))
                    .neuterYn(String.valueOf(tmp.get("neuterYn")))
                    .specialMark(String.valueOf(tmp.get("specialMark")))
                    .careName(String.valueOf(tmp.get("careNm")))
                    .careTel(String.valueOf(tmp.get("careTel")))
                    .careAddr(String.valueOf(tmp.get("careAddr")))
                    .orgName(String.valueOf(tmp.get("orgNm")))
                    .chargeName(String.valueOf(tmp.get("chargeNm")))
                    .officeTel(String.valueOf(tmp.get("officetel")))
                    .noticeComment(String.valueOf(tmp.get("noticeComment")))
                    .build();
            lostDogRepository.save(lostDog);
        }
        return totalCount;
    }

    @Override
    public List<Kind> selectKind(String input) {

        return kindRepository.findByKindNmLike(input);
    }

    @Override
    public List<LostDog> selectListDog(String region, String kind) {
        return lostDogRepository.findByCareAddrLikeAndKindLike(region, kind);
    }


}
