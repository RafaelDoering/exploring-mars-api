package com.rafaeldoering.mars.util;

import lombok.Data;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.rafaeldoering.mars.exception.InvalidCommandException;

@Data
@AllArgsConstructor
public class Position {
  @NotNull(message = "'x' is required")
  @Min(value = 0, message = "'x' must be a positive number")
  private Long x;

  @NotNull(message = "'y' is required")
  @Min(value = 0, message = "'y' must be a positive number")
  private Long y;

  public void move(Character orientation) {
    switch (orientation) {
      case 'N':
        this.setY(this.getY() + 1);
        break;
      case 'E':
        this.setX(this.getX() + 1);
        break;
      case 'S':
        this.setY(this.getY() - 1);
        break;
      case 'W':
        this.setX(this.getX() - 1);
        break;
      default:
        throw new InvalidCommandException();
    }
  }
}