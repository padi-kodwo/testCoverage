package com.padi.testcoverage.error.handler;

import com.padi.testcoverage.dto.ApiResponse;
import com.padi.testcoverage.dto.BaseError;
import com.padi.testcoverage.enums.ResponseMessage;
import com.padi.testcoverage.error.OkException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger EXECPTION_LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = OkException.class)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<Object> handleOkException(OkException ex, WebRequest webRequest){
    HttpHeaders httpHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.OK;

    ApiResponse<BaseError> errorResponse = getErrorResponse(ex, webRequest, ex.getCode());

    EXECPTION_LOGGER.warn("OkException ::: {}", ex.getMessage());
    return handleExceptionInternal(ex, errorResponse, httpHeaders, httpStatus,webRequest);
  }

  private ApiResponse<BaseError> getErrorResponse(Exception exception, WebRequest request, int errorCode) {
    BaseError baseError = new BaseError();
    baseError.setUrl(getUrl(request));
    baseError.setErrorCode(errorCode);
    baseError.setErrorMessage(exception.getMessage());

    ApiResponse<BaseError> errorResponse = new ApiResponse<>();
    errorResponse.setCode(ResponseMessage.FAILED.getCode());
    errorResponse.setMessage(ResponseMessage.FAILED.getMessage());
    errorResponse.setError(baseError);

    logger.error("[ HTTP ERROR: {}", exception);
    return errorResponse;
  }

  private String getUrl(WebRequest webRequest) {
    if (webRequest instanceof ServletWebRequest servletRequest) {
      HttpServletRequest request = servletRequest.getNativeRequest(HttpServletRequest.class);
      return request != null ? request.getRequestURI() : "n/a";
    }
    return null;
  }
}
