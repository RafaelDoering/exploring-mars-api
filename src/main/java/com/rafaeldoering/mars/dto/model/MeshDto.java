package com.rafaeldoering.mars.dto.model;

import lombok.Data;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.rafaeldoering.mars.model.Mesh;

@Data
@AllArgsConstructor
public class MeshDto {
  @NotNull(message = "'name' is required")
  private String name;

  @NotNull(message = "'sizeX' is required")
  @Min(value = 1, message = "'sizeX' must be greater than 0")
  private Long sizeX;

  @NotNull(message = "'sizeY' is required")
  @Min(value = 1, message = "'sizeY' must be greater than 0")
  private Long sizeY;

  public static Mesh toMesh(MeshDto meshDto) {
    return new Mesh(meshDto.name, meshDto.sizeX, meshDto.sizeY);
  }
}
