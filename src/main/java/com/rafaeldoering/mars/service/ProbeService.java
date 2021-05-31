package com.rafaeldoering.mars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rafaeldoering.mars.model.Mesh;
import com.rafaeldoering.mars.model.Probe;

import java.util.List;

import com.rafaeldoering.mars.exception.OccupiedPositionException;
import com.rafaeldoering.mars.exception.OutOfBoundsException;
import com.rafaeldoering.mars.repository.ProbeRepository;

@Service
public class ProbeService {
  @Autowired
  ProbeRepository probeRepository;

  @Autowired
  MeshService meshService;

  public Probe createProbe(String name, Long meshId, Long positionX, Long positionY, String orientation) {
    Mesh mesh = meshService.getMesh(meshId);

    if (positionX > mesh.getSizeX() || positionY > mesh.getSizeY()) {
      throw new OutOfBoundsException();
    }

    List<Probe> probesInThisMesh = probeRepository.findByMesh(mesh);

    for (int i = 0; i < probesInThisMesh.size(); i++) {
      if (positionX.equals(probesInThisMesh.get(i).getPositionX())
          && positionY.equals(probesInThisMesh.get(i).getPositionY())) {
        throw new OccupiedPositionException();
      }
    }

    Probe probe = new Probe(name, mesh, positionX, positionY, orientation);
    probeRepository.save(probe);

    return probe;
  }

  public Page<Probe> getProbes(Pageable pageable) {
    return probeRepository.findAll(pageable);
  }
}
