package com.munggle.dog.repository;

import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.mapper.DogMapper;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MatchingQueryRepository {

    private final EntityManager em;

    public List<DogDetailDto> findDogFriends(User user, Boolean isNeutering, List<String> characters, List<Long> selectionList){

        String query = "SELECT d "
                + "FROM Dog d WHERE d.isMatching = TRUE " + "AND d.user <> :userId ";

        // 중성화 여부
        query += "AND d.isNeutering = :isNeutering ";

        // 이미 선별된 반려견 제외
        if(!selectionList.isEmpty()) {
            query += "AND d.dogId NOT IN :list ";
        }

        // 특징에 따른 선별
        query += "AND ( ";

        for(int idx=0; idx<characters.size(); idx++) {
            query += "d.characterId like '%";
            query+= characters.get(idx);
            if (idx == characters.size() -1)
                continue;
            query+="%' OR ";
        }

        query += "%' ) ";

        TypedQuery<Dog> queryResult = em.createQuery(query, Dog.class);

        queryResult.setParameter("userId", user);
        if(!selectionList.isEmpty())
            queryResult.setParameter("list", selectionList);
        queryResult.setParameter("isNeutering", isNeutering.booleanValue());

        // 20마리만 리턴
        queryResult.setMaxResults(20);

        List<DogDetailDto> result = queryResult.getResultList().stream().map(item -> DogMapper.toDetailDto(item)).collect(Collectors.toList());

        return result;
    }
}
