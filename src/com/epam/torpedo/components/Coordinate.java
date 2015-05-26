package com.epam.torpedo.components;

import java.util.Random;

public class Coordinate {

  private int positionX;
  private int positionY;

  public Coordinate(Coordinate coordinate) {
    this.positionX = coordinate.getX();
    this.positionY = coordinate.getY();
  }

  public Coordinate(int x, int y) {
    this.positionX = x;
    this.positionY = y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + positionX;
    result = prime * result + positionY;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Coordinate other = (Coordinate) obj;
    if (positionX != other.positionX) {
      return false;
    }
    if (positionY != other.positionY) {
      return false;
    }
    return true;
  }

  public int getX() {
    return positionX;
  }

  public int getY() {
    return positionY;
  }

  public void setX(int positionX) {
    this.positionX = positionX;
  }

  public void setY(int positionY) {
    this.positionY = positionY;
  }

  public static Coordinate getRandomCoordinate(int maxXPosition, int maxYPosition) {
    Random random = new Random();
    int positionX = random.nextInt(maxXPosition);
    int positionY = random.nextInt(maxYPosition);
    return new Coordinate(positionX, positionY);
  }

  public static Coordinate getRandomIntervalCoordinate(int minXPosition, int maxXPosition, int minYPosition, int maxYPosition) {
    Random random = new Random();
    int positionX = random.nextInt(maxXPosition - minXPosition) + minXPosition;
    int positionY = random.nextInt(maxYPosition - minYPosition) + minYPosition;
    return new Coordinate(positionX, positionY);
  }

  @Override
  public String toString() {
    return positionX + " " + positionY;
  }

}
