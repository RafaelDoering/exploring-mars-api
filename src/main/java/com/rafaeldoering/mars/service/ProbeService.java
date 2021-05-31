package com.rafaeldoering.mars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rafaeldoering.mars.model.Mesh;
import com.rafaeldoering.mars.model.Probe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rafaeldoering.mars.exception.OccupiedPositionException;
import com.rafaeldoering.mars.exception.OutOfBoundsException;
import com.rafaeldoering.mars.exception.ProbeNotFoundException;
import com.rafaeldoering.mars.repository.ProbeRepository;
import com.rafaeldoering.mars.util.Position;

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

  public Probe getProbe(Long id) {
    Optional<Probe> probe = probeRepository.findById(id);

    if (probe.isEmpty()) {
      throw new ProbeNotFoundException();
    }

    return probe.get();
  }

  public Probe moveProbe(Long id, String commands) {
    Probe probe = getProbe(id);
    Mesh mesh = probe.getMesh();

    List<Probe> probesInThisMesh = probeRepository.findByMesh(probe.getMesh());

    for (int i = 0; i < commands.length(); i++) {
      if (commands.charAt(i) == 'L' || commands.charAt(i) == 'R') {
        probe.setOrientation(
            String.valueOf(getOrientationOnRotation(probe.getOrientation().charAt(0), commands.charAt(i))));
      } else {
        Position position = new Position(probe.getPositionX(), probe.getPositionY());
        position.move(probe.getOrientation().charAt(0));

        if (position.getX() < 0 || position.getY() < 0 || position.getX() > mesh.getSizeX() - 1
            || position.getY() > mesh.getSizeY() - 1) {
          throw new OutOfBoundsException();
        }

        probe.setPositionX(position.getX());
        probe.setPositionY(position.getY());

        probesInThisMesh.forEach((probeInThisMesh) -> {
          if (!probe.getId().equals(probeInThisMesh.getId())
              && (probe.getPositionX().equals(probeInThisMesh.getPositionX())
                  && probe.getPositionY().equals(probeInThisMesh.getPositionY()))) {
            throw new OccupiedPositionException();
          }
        });
      }
    }

    return probeRepository.save(probe);
  }

  private Character getOrientationOnRotation(Character actualOrientation, Character rotation) {
    List<Character> coordinates = new ArrayList<>();

    coordinates.add('N');
    coordinates.add('E');
    coordinates.add('S');
    coordinates.add('W');

    int actualOrientationIndex = coordinates.indexOf(actualOrientation);
    if (rotation == 'L') {
      actualOrientationIndex = actualOrientationIndex - 1;
      if (actualOrientationIndex < 0) {
        actualOrientationIndex = actualOrientationIndex + coordinates.size();
      }
    } else if (rotation == 'R') {
      actualOrientationIndex = actualOrientationIndex + 1;
      if (actualOrientationIndex >= coordinates.size()) {
        actualOrientationIndex = actualOrientationIndex - coordinates.size();
      }
    }

    return coordinates.get(actualOrientationIndex);
  }
}
