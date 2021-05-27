package com.rafaeldoering.mars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaeldoering.mars.model.Mesh;

import com.rafaeldoering.mars.repository.MeshRepository;

@Service
public class MeshService {
  @Autowired
  MeshRepository meshRepository;

  public Mesh createMesh(Mesh mesh) {
    meshRepository.save(mesh);

    return mesh;
  }
}
