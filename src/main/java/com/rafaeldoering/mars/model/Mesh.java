package com.rafaeldoering.mars.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "meshs")
public class Mesh implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @Min(value = 1, message = "'sizeX' must be greater than 0")
  private Long sizeX;

  @Min(value = 1, message = "'sizeY' must be greater than 0")
  private Long sizeY;

  @OneToMany(mappedBy = "mesh", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Probe.class)
  @JsonIgnoreProperties(value = { "mesh" })
  private List<Probe> probes;

  public Mesh() {
  }

  public Mesh(String name, Long sizeX, Long sizeY) {
    this.name = name;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
  }
}