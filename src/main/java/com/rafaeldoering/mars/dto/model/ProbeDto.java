package com.rafaeldoering.mars.dto.model;

import lombok.Data;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class ProbeDto {
  @NotNull(message = "'name' is required")
  private String name;

  @NotNull(message = "'meshId' is required")
  private Long meshId;

  @NotNull(message = "'positionX' is required")
  @Min(value = 0, message = "'positionX' must be a positive number")
  private Long positionX;

  @NotNull(message = "'positionY' is required")
  @Min(value = 0, message = "'positionY' must be a positive number")
  private Long positionY;

  @NotNull(message = "'orientation' is required")
  @Pattern(regexp = "[NESW]", message = "'orientation' must be 'N', 'E', 'S' or 'W'")
  private String orientation;
}
