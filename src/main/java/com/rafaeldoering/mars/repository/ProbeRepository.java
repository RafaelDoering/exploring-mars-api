package com.rafaeldoering.mars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.rafaeldoering.mars.model.Mesh;
import com.rafaeldoering.mars.model.Probe;

@Repository
public interface ProbeRepository extends JpaRepository<Probe, Long> {
  Page<Probe> findAll(Pageable pageable);

  List<Probe> findByMesh(Mesh mesh);

  Optional<Probe> findById(Long id);
}
