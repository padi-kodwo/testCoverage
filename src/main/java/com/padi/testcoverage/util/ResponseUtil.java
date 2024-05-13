package com.padi.testcoverage.util;

import com.padi.testcoverage.dto.ApiResponse;
import com.padi.testcoverage.enums.ResponseMessage;
import java.io.Serializable;

public class ResponseUtil {

  private ResponseUtil() {
  }

  public static <T extends Serializable> ApiResponse<T> wrapInApiResponse(T data) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setCode(ResponseMessage.SUCCESS.getCode());
    response.setMessage(ResponseMessage.SUCCESS.getMessage());
    response.setData(data);
    return response;
  }
}
