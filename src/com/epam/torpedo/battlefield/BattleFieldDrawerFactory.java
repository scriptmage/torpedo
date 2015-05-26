package com.epam.torpedo.battlefield;

import com.epam.torpedo.Drawable;
import com.epam.torpedo.battlefield.drawers.BattleFieldDrawer;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.resolvers.Resolver;

public class BattleFieldDrawerFactory {

  private static Drawable drawer;

  public static Drawable getDrawer() {
    Resolver resolver = GameConfig.getResolver();
    String battleFieldDrawerName = resolver.get("battleFieldDrawer");

    if (drawer == null) {
      switch (battleFieldDrawerName.trim()) {
        case "console":
          drawer = new BattleFieldDrawer();
          break;
      }
    }

    return drawer;
  }

}
