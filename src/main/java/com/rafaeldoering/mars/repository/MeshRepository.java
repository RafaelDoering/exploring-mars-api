package com.rafaeldoering.mars.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rafaeldoering.mars.model.Mesh;

@Repository
public interface MeshRepository extends PagingAndSortingRepository<Mesh, Long> {
}
