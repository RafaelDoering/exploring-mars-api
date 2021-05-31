package com.rafaeldoering.mars.exception;

public class InvalidCommandException extends RuntimeException {
  public InvalidCommandException() {
    super("Invalid command");
  }
}
