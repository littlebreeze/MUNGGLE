package com.munggle.openapitest;

import com.munggle.domain.model.entity.Kind;
import com.munggle.domain.model.entity.LostDog;
import com.munggle.openAPI.repository.KindRepository;
import com.munggle.openAPI.repository.LostDogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OpenAPITests {

	@Autowired
	KindRepository kindRepository;
	@Autowired
	LostDogRepository lostDogRepository;

	@Test
	public void selectKindLikeTest(){
		List<Kind> list = kindRepository.findByKindNmLike("%리%");
		assertEquals(81,list.size());
	}

	@Test
	public void selectLostDogTest(){
		List<LostDog> list = lostDogRepository.findByCareAddrLikeAndKindLike("%%","%믹스견");
		assertEquals(5365,list.size());
	}

}
