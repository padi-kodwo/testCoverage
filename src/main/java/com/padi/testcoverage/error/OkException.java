package com.padi.testcoverage.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkException extends RuntimeException{

  private final int code;
  private final String message;

  public OkException(int code, String message){
    this.code = code;
    this.message = message;
  }
}
