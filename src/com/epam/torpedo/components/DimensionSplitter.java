package com.epam.torpedo.components;

import com.epam.torpedo.game.GameConfig;

public class DimensionSplitter {

  private int       counterOfBlocks;
  private int       numberOfBlocks;
  private Dimension dimension;

  public DimensionSplitter(int numberOfBlocks) {
    this.numberOfBlocks = numberOfBlocks;
    dimension = GameConfig.getDimension();
  }

  public void next() {
    counterOfBlocks++;
    if (counterOfBlocks > numberOfBlocks - 1) {
      counterOfBlocks = 0;
    }
  }

  public Coordinate getPosition() {
    int divider = (int) Math.sqrt(numberOfBlocks);
    return Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / divider * (counterOfBlocks % divider), dimension.getWidth() / divider
        * (counterOfBlocks % divider) + dimension.getWidth() / divider, dimension.getHeight() / divider * (counterOfBlocks / divider), dimension.getHeight()
        / divider * (counterOfBlocks / divider) + dimension.getHeight() / divider);
  }

}
