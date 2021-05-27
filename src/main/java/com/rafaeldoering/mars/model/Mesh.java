package com.rafaeldoering.mars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity
public class Mesh {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Min(value = 1, message = "'sizeX' must be greater than 0")
  private Long sizeX;

  @Min(value = 1, message = "'sizeY' must be greater than 0")
  private Long sizeY;

  public Mesh() {
  }

  public Mesh(String name, Long sizeX, Long sizeY) {
    this.name = name;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
  }
}