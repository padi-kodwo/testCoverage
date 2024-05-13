package com.padi.testcoverage.dto;


import com.padi.testcoverage.GlobalConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateUserDtoTest {

  @Test
  void constructor1() {
    CreateUserDto dto = new CreateUserDto();

    Assertions.assertEquals(new CreateUserDto(), dto);
  }

  @Test
  void constructor2() {
    CreateUserDto dto = new CreateUserDto(GlobalConstants.TEST_USER_1_NAME, GlobalConstants.TEST_USER_1_DOB);

    Assertions.assertEquals(GlobalConstants.TEST_USER_1_NAME, dto.getName());
    Assertions.assertEquals(GlobalConstants.TEST_USER_1_DOB, dto.getDateOfBirth());
  }

  @Test
  void testToString() {
    String str = new CreateUserDto().toString();

    Assertions.assertTrue(str.contains("name"));
  }
}