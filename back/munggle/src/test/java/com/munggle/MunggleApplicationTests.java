package com.munggle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"prod", "common"})
@SpringBootTest
class MunggleApplicationTests {

	@Test
	void contextLoads() {
	}

}
