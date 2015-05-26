package com.epam.torpedo.targets.ships.concrete;

import com.epam.torpedo.Ship;

public class NullShip extends Ship {

  @Override
  public void createShape() {

  }

  @Override
  public boolean isAlive() {
    return false;
  }

}
