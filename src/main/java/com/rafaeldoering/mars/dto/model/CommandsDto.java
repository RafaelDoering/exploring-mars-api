package com.rafaeldoering.mars.dto.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandsDto {
  @NotNull(message = "'commands' is required")
  @Pattern(regexp = "^[LRM]+$", message = "'commands' must contain only 'L', 'R' and 'M'")
  private String commands;
}
