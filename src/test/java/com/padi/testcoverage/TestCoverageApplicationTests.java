package com.padi.testcoverage;

import com.padi.testcoverage.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestCoverageApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertNotNull(UserController.class);
	}

}
