package com.padi.testcoverage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class BaseError implements Serializable {
  @Serial
  private static final long serialVersionUID = -2518961146692095092L;

  @Schema(description = "Error code")
  private int errorCode;

  @Schema(description = "Error message")
  private String errorMessage;

  @Schema(description = "Error url")
  private String url;
}
