package com.epam.torpedo;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;

public abstract class Hunter implements Shooter {
  protected Dimension   dimension;
  private CoordinateSet shots;
  private Coordinate    lastShot;

  public void setShotsContainer(CoordinateSet coordinateSet) {
    shots = coordinateSet;
  }

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  public void setDimension(int widthOfBattleField, int heightOfBattleField) {
    setDimension(new Dimension(widthOfBattleField, heightOfBattleField));
  }

  public void addShot(int x, int y) {
    addShot(new Coordinate(x, y));
  }

  public void addShot(Coordinate coordinate) {
    lastShot = coordinate;
    shots.add(coordinate);
  }

  public void addShots(CoordinateSet coordinateSet) {
    shots.addAll(coordinateSet);
  }

  public boolean isExists(int x, int y) {
    return isExists(new Coordinate(x, y));
  }

  public boolean isExists(Coordinate coordinate) {
    return shots.contains(coordinate);
  }

  public CoordinateSet getShots() {
    CoordinateSet result = new CoordinateSet();
    result.addAll(shots);
    return result;
  }

  public Coordinate getLastShot() {
    return lastShot;
  }

  public abstract Coordinate nextShot();

  public abstract void setPosition(Coordinate coordinate);

  public abstract void clear();
}
