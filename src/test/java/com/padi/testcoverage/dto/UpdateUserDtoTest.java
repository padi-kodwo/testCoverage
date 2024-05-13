package com.padi.testcoverage.dto;

import com.padi.testcoverage.GlobalConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateUserDtoTest {

  @Test
  void constructor1() {
    UpdateUserDto dto = new UpdateUserDto();

    Assertions.assertEquals(new UpdateUserDto(), dto);
  }

  @Test
  void constructor2() {
    UpdateUserDto dto = new UpdateUserDto(GlobalConstants.TEST_USER_ID, GlobalConstants.TEST_USER_1_NAME, GlobalConstants.TEST_USER_1_DOB);

    Assertions.assertEquals(GlobalConstants.TEST_USER_ID, dto.getId());
    Assertions.assertEquals(GlobalConstants.TEST_USER_1_NAME, dto.getName());
    Assertions.assertEquals(GlobalConstants.TEST_USER_1_DOB, dto.getDateOfBirth());
  }

  @Test
  void testToString() {
    String str = new UpdateUserDto().toString();

    Assertions.assertTrue(str.contains("name"));
    Assertions.assertTrue(str.contains("id"));
  }

}