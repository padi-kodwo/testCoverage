package com.padi.testcoverage.dto;

import static com.padi.testcoverage.GlobalConstants.*;

import com.padi.testcoverage.enums.ResponseMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApiResponseTest {

  @Test
  void constructor1() {
    ApiResponse<String> dto = new ApiResponse<>();

    Assertions.assertEquals(new ApiResponse<String>(), dto);
  }

  @Test
  void data() {
    ApiResponse<String> dto = new ApiResponse<>();
    dto.setCode(ResponseMessage.SUCCESS.getCode());
    dto.setData(TEST_STRING_1);
    dto.setMessage(ResponseMessage.SUCCESS.getMessage());
    dto.setError(new BaseError());

    ApiResponse<String> expected = dto;

    Assertions.assertEquals(TEST_STRING_1, dto.getData());
    Assertions.assertEquals(ResponseMessage.SUCCESS.getCode(), dto.getCode());
    Assertions.assertEquals(ResponseMessage.SUCCESS.getMessage(), dto.getMessage());
    Assertions.assertEquals(new BaseError(), dto.getError());
    Assertions.assertEquals(expected.toString(), dto.toString());
    Assertions.assertEquals(expected.hashCode(), dto.hashCode());
    Assertions.assertEquals(expected, dto);
  }


  @Test
  void testToString() {
    String str = new ApiResponse<String>().toString();

    Assertions.assertTrue(str.contains("code"));
    Assertions.assertTrue(str.contains("message"));
  }

}