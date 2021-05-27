package com.rafaeldoering.mars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rafaeldoering.mars.model.Mesh;

import java.util.Optional;

import com.rafaeldoering.mars.exception.MeshNotFoundException;
import com.rafaeldoering.mars.repository.MeshRepository;

@Service
public class MeshService {
  @Autowired
  MeshRepository meshRepository;

  public Mesh createMesh(Mesh mesh) {
    meshRepository.save(mesh);

    return mesh;
  }

  public Page<Mesh> getMeshs(Pageable pageable) {
    return meshRepository.findAll(pageable);
  }

  public Mesh getMesh(Long id) {
    Optional<Mesh> mesh = meshRepository.findById(id);

    if (mesh.isEmpty()) {
      throw new MeshNotFoundException();
    }

    return mesh.get();
  }

  public Mesh deleteMesh(Long id) {
    Optional<Mesh> mesh = meshRepository.findById(id);

    if (mesh.isEmpty()) {
      throw new MeshNotFoundException();
    }

    meshRepository.deleteById(id);

    return mesh.get();
  }
}
