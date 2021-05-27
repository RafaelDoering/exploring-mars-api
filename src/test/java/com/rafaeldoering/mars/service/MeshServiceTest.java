package com.rafaeldoering.mars.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rafaeldoering.mars.repository.MeshRepository;
import com.rafaeldoering.mars.model.Mesh;

@SpringBootTest
class MeshServiceTest {
  @Mock
  private MeshRepository meshRepository;

  @InjectMocks
  private MeshService meshService = new MeshService();

  @BeforeEach
  void setMockOutput() {
    Mesh mesh = new Mesh("Mars", 1L, 1L);
    List<Mesh> meshList = new ArrayList<Mesh>();
    meshList.add(mesh);
    when(meshRepository.findAll()).thenReturn(meshList);
    when(meshRepository.findById(0L)).thenReturn(Optional.of(mesh));
  }

  @Test
  @DisplayName("Should create and return mesh")
  void createMesh_shouldCreateAndReturnMesh() {
    Mesh newMesh = meshService.createMesh(new Mesh("Mars 3", 1L, 1L));
    Assertions.assertNotNull(newMesh);
    Assertions.assertEquals("Mars 3", newMesh.getName());
  }

  @Test
  @DisplayName("Should return meshs")
  void getMeshs_shouldReturnMeshList() {
    Assertions.assertNotNull(meshService.getMeshs(PageRequest.of(0, 2)));
    Assertions.assertEquals("Mars", meshService.getMeshs(PageRequest.of(0, 2)).getContent().get(0).getName());
  }

  @Test
  @DisplayName("Should find Mars mesh")
  void getMesh_shouldFindAMesh() {
    Mesh mesh = meshService.getMesh(0L);
    Assertions.assertNotNull(mesh);
    Assertions.assertEquals("Mars", mesh.getName());
  }

  @Test
  @DisplayName("Should delete and return mesh")
  void deleteMesh_shouldDeleteAndReturnMesh() {
    Mesh mesh = meshService.deleteMesh(0L);
    Assertions.assertNotNull(mesh);
    Assertions.assertEquals("Mars", mesh.getName());
  }
}