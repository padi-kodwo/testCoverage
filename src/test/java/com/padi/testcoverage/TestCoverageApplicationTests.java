package com.padi.testcoverage;

import com.padi.testcoverage.controller.UserController;
import java.util.Date;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestCoverageApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertNotNull(UserController.class);
	}

	@Test
	void test(){
		System.out.println("Upgrade mag-updates project to SADS plugin 1011".replace(" ", "-").toLowerCase());
	}


}
