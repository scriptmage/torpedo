package com.epam.torpedo.targets;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;

import java.util.Iterator;

public class Shape implements Comparable<Shape> {
  private CoordinateSet points;
  private Coordinate    topLeft;
  private Coordinate    bottomRight;

  public Shape() {
    points = new CoordinateSet();
  }

  public CoordinateSet getPoints() {
    CoordinateSet coordinateSet = new CoordinateSet();
    coordinateSet.addAll(points);
    return coordinateSet;
  }

  public Iterator<Coordinate> iterator() {
    return points.iterator();
  }

  public boolean contains(int x, int y) {
    return points.contains(x, y);
  }

  public int size() {
    return points.size();
  }

  public boolean add(int x, int y) {
    return points.add(x, y);
  }

  public int getTop() {
    return topLeft.getY();
  }

  public int getBottom() {
    return bottomRight.getY();
  }

  public int getLeft() {
    return topLeft.getX();
  }

  public int getRight() {
    return bottomRight.getX();
  }

  public void calcArea() {
    topLeft = calcTopLeftCoordinate();
    bottomRight = calcBottomRightCoordinate();
  }

  private Coordinate calcTopLeftCoordinate() {
    int positionX = Integer.MAX_VALUE;
    int positionY = Integer.MAX_VALUE;

    Iterator<Coordinate> iterator = points.iterator();
    while (iterator.hasNext()) {
      Coordinate coordinate = iterator.next();
      if (positionX > coordinate.getX()) {
        positionX = coordinate.getX();
      }
      if (positionY > coordinate.getY()) {
        positionY = coordinate.getY();
      }
    }

    return new Coordinate(positionX, positionY);
  }

  private Coordinate calcBottomRightCoordinate() {
    int positionX = 0;
    int positionY = 0;

    Iterator<Coordinate> iterator = points.iterator();
    while (iterator.hasNext()) {
      Coordinate coordinate = iterator.next();
      if (positionX < coordinate.getX()) {
        positionX = coordinate.getX();
      }
      if (positionY < coordinate.getY()) {
        positionY = coordinate.getY();
      }
    }
    return new Coordinate(positionX, positionY);
  }

  @Override
  public int compareTo(Shape shape) {
    return shape.size() - this.size();
  }

}
