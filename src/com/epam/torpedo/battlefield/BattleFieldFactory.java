package com.epam.torpedo.battlefield;

import com.epam.torpedo.battlefield.concrete.FileBattleField;
import com.epam.torpedo.battlefield.concrete.RandomBattleField;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.resolvers.Resolver;

public class BattleFieldFactory {

	private static BattleField battleField;

	public static BattleField getBattleField() {
		Resolver resolver = GameConfig.getResolver();
		String battleFieldFillStrategyName = resolver.get("battleField");

		if (battleField == null) {
			switch (battleFieldFillStrategyName.trim()) {
			case "random":
				battleField = new RandomBattleField();
				String numberOfShips = resolver.get("numberOfShips");
				battleField.setMaxNumberOfShips(Integer.parseInt(numberOfShips));
				break;
			case "file":
				battleField = new FileBattleField();
				break;
			default:
				throw new IllegalArgumentException("Unknown battlefield type: " + battleFieldFillStrategyName + "! Use the following: random, file");
			}
		}

		return battleField;
	}

}
