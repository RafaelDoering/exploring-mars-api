package com.rafaeldoering.mars.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rafaeldoering.mars.repository.ProbeRepository;
import com.rafaeldoering.mars.model.Mesh;
import com.rafaeldoering.mars.model.Probe;

@SpringBootTest
class ProbeServiceTest {
  @Mock
  private MeshService meshService;

  @Mock
  private ProbeRepository probeRepository;

  @InjectMocks
  private ProbeService probeService = new ProbeService();

  @BeforeEach
  void setMockOutput() {
    Mesh mesh = new Mesh("Mars", 10L, 10L);
    Probe probe = new Probe(1L, "Probe 1", mesh, 1L, 0L, "N");
    List<Probe> probeList = new ArrayList<Probe>();
    probeList.add(probe);

    when(meshService.getMesh(0L)).thenReturn(mesh);
    when(probeRepository.findByMesh(any(Mesh.class))).thenReturn(probeList);
    when(probeRepository.findById(0L)).thenReturn(Optional.of(probe));
    when(probeRepository.save(probe)).thenReturn(probe);
  }

  @Test
  @DisplayName("Should create and return probe")
  void createProbe_shouldCreateAndReturnProbe() {
    Probe probe = probeService.createProbe("Probe 2", 0L, 0L, 0L, "N");
    Assertions.assertNotNull(probe);
    Assertions.assertEquals("Probe 2", probe.getName());
  }

  @Test
  @DisplayName("Should move and return probe")
  void moveProbe_shouldMoveAndReturnProbe() {
    Probe probe = probeService.moveProbe(0L, "MMM");
    Assertions.assertNotNull(probe);
    Assertions.assertEquals(3L, probe.getPositionY());
  }
}