package com.padi.testcoverage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse<T extends Serializable> implements Serializable {

  @Serial
  private static final long serialVersionUID = -1915951285920732398L;

  @Schema(description = "Response code")
  private int code;

  @Schema(description = "Response message")
  private String message;

  @Schema(description = "Response data")
  private T data;

  @Schema(description = "Error details")
  private BaseError error;
}
