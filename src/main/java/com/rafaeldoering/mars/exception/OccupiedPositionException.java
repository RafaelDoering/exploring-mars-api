package com.rafaeldoering.mars.exception;

public class OccupiedPositionException extends RuntimeException {
  public OccupiedPositionException() {
    super("Occupied position");
  }
}
