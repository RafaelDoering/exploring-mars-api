package com.rafaeldoering.mars.util;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ExceptionResponse {
  private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
  private List<String> errors;
}
