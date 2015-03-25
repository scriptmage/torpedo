package com.epam.torpedo.board;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.board.battlefields.FileBattleField;
import com.epam.torpedo.board.battlefields.RandomBattleField;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.resolvers.Resolver;

public class BattleFieldFactory {

	private static BattleField battleField;

	public static BattleField createBattleField() {
		Resolver resolver = Config.getResolver();
		String battleFieldFillStrategyName = resolver.get("battleField");

		if (battleField == null) {
			switch (battleFieldFillStrategyName) {
			case "random":
				String numberOfShips = resolver.get("numberOfShips");
				battleField = new RandomBattleField(Integer.parseInt(numberOfShips));
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
