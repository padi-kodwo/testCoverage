package com.padi.testcoverage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto implements Serializable {

  @NotBlank
  @Schema(description = "User's id")
  private String id;

  @NotBlank
  @Schema(description = "User's name")
  private String name;

  @Schema(description = "User's date of birth")
  private String dateOfBirth;
}
