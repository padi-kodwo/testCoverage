package com.padi.testcoverage.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.padi.testcoverage.GlobalConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserDtoTest {
  @Test
  void constructor1() {
    UserDto dto = new UserDto();

    Assertions.assertEquals(new UserDto(), dto);
  }

  @Test
  void constructor2() {
    UserDto dto = new UserDto();
    dto.setId(GlobalConstants.TEST_USER_ID);
    dto.setName(GlobalConstants.TEST_USER_1_NAME);
    dto.setDateOfBirth(GlobalConstants.TEST_USER_1_DOB);
    dto.setCreatedAt(GlobalConstants.TEST_USER_1_DOB);
    dto.setModifiedAt(GlobalConstants.TEST_USER_1_DOB);

    Assertions.assertEquals(GlobalConstants.TEST_USER_ID, dto.getId());
    Assertions.assertEquals(GlobalConstants.TEST_USER_1_NAME, dto.getName());
    Assertions.assertEquals(GlobalConstants.TEST_USER_1_DOB, dto.getDateOfBirth());
    Assertions.assertEquals(GlobalConstants.TEST_USER_1_DOB, dto.getCreatedAt());
    Assertions.assertEquals(GlobalConstants.TEST_USER_1_DOB, dto.getModifiedAt());
  }

  @Test
  void testToString() {
    String str = new UserDto().toString();

    Assertions.assertTrue(str.contains("name"));
    Assertions.assertTrue(str.contains("id"));
    Assertions.assertTrue(str.contains("dateOfBirth"));
  }


}