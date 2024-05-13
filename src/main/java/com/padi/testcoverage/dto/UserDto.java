package com.padi.testcoverage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class UserDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 6613471837742199375L;

  @Schema(description = "User's unique id")
  private String id;

  @NotBlank
  @Schema(description = "User's name")
  private String name;

  @Schema(description = "User's date of birth")
  private String dateOfBirth;

  @Schema(description = "Date user was created")
  private String createdAt;

  @Schema(description = "Date user was last modified")
  private String modifiedAt;
}
