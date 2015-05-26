package com.epam.torpedo;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.GameConfig;

public class Board {
  private Dimension dimension;

  public Board() {
    dimension = GameConfig.getDimension();
  }

  public Board(Dimension dimension) {
    this.dimension = dimension;
  }

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  public void setDimension(int widthOfBattleField, int heightOfBattleField) {
    setDimension(new Dimension(widthOfBattleField, heightOfBattleField));
  }

  public int getWidth() {
    return dimension.getWidth();
  }

  public int getHeight() {
    return dimension.getHeight();
  }

  public Dimension getDimension() {
    return dimension;
  }

  public boolean validatePosition(Coordinate coordinate) {
    return validatePosition(coordinate.getX(), coordinate.getY());
  }

  public boolean validatePosition(int x, int y) {
    if (!isValidPositionX(x)) {
      throw new IllegalArgumentException("Illegal X [" + x + "] position, must be between 0 and " + (dimension.getWidth() - 1));
    }
    if (!isValidPositionY(y)) {
      throw new IllegalArgumentException("Illegal Y [" + y + "] position, must be between 0 and " + (dimension.getHeight() - 1));
    }
    return true;
  }

  private boolean isValidPositionX(int x) {
    return x >= 0 && x < dimension.getWidth();
  }

  private boolean isValidPositionY(int y) {
    return y >= 0 && y < dimension.getHeight();
  }

}
