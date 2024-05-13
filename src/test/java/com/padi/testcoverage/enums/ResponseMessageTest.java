package com.padi.testcoverage.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResponseMessageTest {

  @Test
  void constructor() {
    ResponseMessage actual = ResponseMessage.SUCCESS;

    Assertions.assertEquals(ResponseMessage.SUCCESS, actual);
    Assertions.assertEquals(ResponseMessage.SUCCESS.getCode(), actual.getCode());
    Assertions.assertEquals(ResponseMessage.SUCCESS.getMessage(), actual.getMessage());
  }

  @Test
  void getByCode() {
    ResponseMessage actual = ResponseMessage.getByCode(-1);

    Assertions.assertEquals(ResponseMessage.FAILED, actual);
    Assertions.assertEquals(ResponseMessage.FAILED.getCode(), actual.getCode());
    Assertions.assertEquals(ResponseMessage.FAILED.getMessage(), actual.getMessage());
  }
  @Test
  void getByCode_thenReturn_null() {
    ResponseMessage actual = ResponseMessage.getByCode(3);
    Assertions.assertNull(actual);
  }

  @Test
  void values() {
    assertEquals(2, ResponseMessage.values().length);
  }

  @Test
  void valueOf() {
    ResponseMessage actual = ResponseMessage.valueOf("SUCCESS");
    Assertions.assertEquals(ResponseMessage.SUCCESS, actual);
  }
}