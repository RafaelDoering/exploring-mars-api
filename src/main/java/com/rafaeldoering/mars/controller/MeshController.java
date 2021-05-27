package com.rafaeldoering.mars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Operation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.rafaeldoering.mars.model.Mesh;
import com.rafaeldoering.mars.dto.model.MeshDto;
import com.rafaeldoering.mars.exception.MeshNotFoundException;
import com.rafaeldoering.mars.service.MeshService;
import com.rafaeldoering.mars.util.ExceptionResponse;

@RestControllerAdvice
@RequestMapping("/meshs")
public class MeshController {
  private MeshService meshService;

  @Autowired
  public MeshController(MeshService meshService) {
    this.meshService = meshService;
  }

  @Operation(summary = "Create a mesh")
  @PostMapping()
  public Mesh postMesh(@Valid @RequestBody MeshDto mesh) {
    return meshService.createMesh(MeshDto.toMesh(mesh));
  }

  @Operation(summary = "Get all meshs")
  @GetMapping()
  public Page<Mesh> getMeshs(Pageable pageable) {
    return meshService.getMeshs(pageable);
  }

  @Operation(summary = "Get a mesh")
  @GetMapping("/{id}")
  public Mesh getMesh(@PathVariable("id") Long id) {
    return meshService.getMesh(id);
  }

  @Operation(summary = "Delete a mesh")
  @DeleteMapping("/{id}")
  public Mesh deleteMesh(@PathVariable("id") Long id) {
    return meshService.deleteMesh(id);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  private ExceptionResponse meshNotFoundHandler(MeshNotFoundException exception) {
    List<String> errors = new ArrayList<>();
    errors.add(exception.getMessage());
    return new ExceptionResponse(HttpStatus.NOT_FOUND.value(), errors);
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
