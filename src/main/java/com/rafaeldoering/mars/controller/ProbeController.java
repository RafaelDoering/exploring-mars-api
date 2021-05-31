package com.rafaeldoering.mars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Operation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.rafaeldoering.mars.model.Probe;
import com.rafaeldoering.mars.dto.model.ProbeDto;
import com.rafaeldoering.mars.exception.InvalidCommandException;
import com.rafaeldoering.mars.exception.OccupiedPositionException;
import com.rafaeldoering.mars.exception.OutOfBoundsException;
import com.rafaeldoering.mars.exception.ProbeNotFoundException;
import com.rafaeldoering.mars.service.ProbeService;
import com.rafaeldoering.mars.util.ExceptionResponse;

@RestControllerAdvice
@RequestMapping("/probes")
public class ProbeController {
  private ProbeService probeService;

  @Autowired
  public ProbeController(ProbeService probeService) {
    this.probeService = probeService;
  }

  @Operation(summary = "Create a probe")
  @PostMapping()
  public Probe postProbe(@Valid @RequestBody ProbeDto probe) {
    return probeService.createProbe(probe.getName(), probe.getMeshId(), probe.getPositionX(), probe.getPositionY(),
        probe.getOrientation());
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  private ExceptionResponse probeNotFoundHandler(ProbeNotFoundException exception) {
    List<String> errors = new ArrayList<>();
    errors.add(exception.getMessage());
    return new ExceptionResponse(HttpStatus.NOT_FOUND.value(), errors);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ExceptionResponse occupiedPositionHandler(OccupiedPositionException exception) {
    List<String> errors = new ArrayList<>();
    errors.add(exception.getMessage());
    return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errors);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ExceptionResponse outOfBoundsHandler(OutOfBoundsException exception) {
    List<String> errors = new ArrayList<>();
    errors.add(exception.getMessage());
    return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errors);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ExceptionResponse invalidCommandHandler(InvalidCommandException exception) {
    List<String> errors = new ArrayList<>();
    errors.add(exception.getMessage());
    return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errors);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleValidationExceptions(MethodArgumentNotValidException exception) {
    List<String> errors = new ArrayList<>();
    exception.getBindingResult().getAllErrors().forEach((error) -> {
      String errorMessage = error.getDefaultMessage();
      errors.add(errorMessage);
    });
    return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errors);
  }
}
