package com.munggle.openAPI.service;

import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.NotYourDogException;
import com.munggle.domain.exception.OpenAPIErrorException;
import com.munggle.domain.model.entity.Kind;
import com.munggle.domain.model.entity.LostDog;
import com.munggle.openAPI.dto.KindDto;
import com.munggle.openAPI.dto.LostDogDto;
import com.munggle.openAPI.repository.KindRepository;
import com.munggle.openAPI.repository.LostDogRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenAPIServiceImpl implements OpenAPIService {

    private final KindRepository kindRepository;
    private final LostDogRepository lostDogRepository;

    @Override
    public void insertKind(String kindJSON) throws ParserConfigurationException, ParseException, IOException, SAXException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject)jsonParser.parse(kindJSON);
        } catch (ParseException e) {
            throw new OpenAPIErrorException(ExceptionMessage.OPEN_API_RESPONSE_ERROR, errorMessage(kindJSON));
        }
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

            kindRepository.save(kind);
        }
    }

    @Override
    public Long insertLostDog(String lostJSON) throws ParserConfigurationException, ParseException, IOException, SAXException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject)jsonParser.parse(lostJSON);
        } catch (ParseException e) {
            System.out.println(errorMessage(lostJSON));
            throw new OpenAPIErrorException(ExceptionMessage.OPEN_API_RESPONSE_ERROR, errorMessage(lostJSON));
        }
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
    public List<KindDto> selectKind(String input) {

        return kindRepository.findByKindNmContains(input).orElseThrow().stream().map(Kind::toDto).collect(Collectors.toList());
    }

    @Override
    public List<LostDogDto> selectListDog(String region, String kind) {
        return lostDogRepository.findByCareAddrContainsAndKindEndsWith(region, kind).orElseThrow().stream().map(LostDog::toDto).collect(Collectors.toList());
    }


    public String errorMessage(String response) throws ParserConfigurationException, ParseException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new InputSource(new StringReader(response)));
        NodeList msgNode = document.getElementsByTagName("returnAuthMsg");
        NodeList codeNode = document.getElementsByTagName("returnReasonCode");
        String msg = msgNode.item(0).getChildNodes().item(0).getNodeValue();
        String code = codeNode.item(0).getChildNodes().item(0).getNodeValue();
        return msg +"("+ code+")";
    }
}
