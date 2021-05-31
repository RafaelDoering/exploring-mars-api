package com.rafaeldoering.mars.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "probes")
public class Probe implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Mesh.class)
  @JoinColumn(name = "mesh_id", nullable = false)
  @JsonIgnoreProperties(value = { "probes", "hibernateLazyInitializer" })
  private Mesh mesh;

  @Min(value = 0, message = "'positionX' must be a positive number")
  private Long positionX;

  @Min(value = 0, message = "'positionY' must be a positive number")
  private Long positionY;

  @Pattern(regexp = "[NESW]", message = "must be 'N', 'E', 'S' or 'W'")
  private String orientation;

  public Probe() {
  }

  public Probe(String name, Mesh mesh, Long positionX, Long positionY, String orientation) {
    this.name = name;
    this.mesh = mesh;
    this.positionX = positionX;
    this.positionY = positionY;
    this.orientation = orientation;
  }
}