package com.epam.torpedo.game;

import com.epam.torpedo.Startable;

public class Application {

  public static void main(String[] args) {
    System.out.println("BattleShip");
    try {
      Startable game = GameFactory.createGame(args);
      game.start();
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Game Over");
  }

}
