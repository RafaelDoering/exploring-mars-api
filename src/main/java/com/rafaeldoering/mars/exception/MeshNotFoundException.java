package com.rafaeldoering.mars.exception;

public class MeshNotFoundException extends RuntimeException {
  public MeshNotFoundException() {
    super("Mesh not found");
  }
}
