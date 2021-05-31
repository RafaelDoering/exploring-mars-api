package com.rafaeldoering.mars.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

  private Mesh mesh;

  @BeforeEach
  void setMockOutput() {
    mesh = new Mesh("Mars", 1L, 1L);
    Probe probe = new Probe("Probe 1", mesh, 1L, 0L, "N");
    List<Probe> probeList = new ArrayList<Probe>();
    probeList.add(probe);

    when(meshService.getMesh(0L)).thenReturn(mesh);
    when(probeRepository.findByMesh(mesh)).thenReturn(probeList);
  }

  @Test
  @DisplayName("Should create and return probe")
  void createProbe_shouldCreateAndReturnProbe() {
    Probe probe = probeService.createProbe("Probe 2", 0L, 0L, 0L, "N");
    Assertions.assertNotNull(probe);
    Assertions.assertEquals("Probe 2", probe.getName());
  }
}