package com.rafaeldoering.mars.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.rafaeldoering.mars.model.Mesh;

@Repository
public interface MeshRepository extends PagingAndSortingRepository<Mesh, Long> {
  Page<Mesh> findAll(Pageable pageable);

  Optional<Mesh> findById(Long id);
}
