package com.rafaeldoering.mars.exception;

public class ProbeNotFoundException extends RuntimeException {
  public ProbeNotFoundException() {
    super("Probe not found");
  }
}
