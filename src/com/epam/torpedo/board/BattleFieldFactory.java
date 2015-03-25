package com.epam.torpedo.board;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.board.battlefields.FileBattleField;
import com.epam.torpedo.board.battlefields.RandomBattleField;
import com.epam.torpedo.resolvers.GameModeResolver;
import com.epam.torpedo.resolvers.datasources.PropertyReader;

public class BattleFieldFactory {

	private static BattleField battleField;

	public static BattleField createBattleField() {

		if (battleField == null) {
			GameModeResolver options = new PropertyReader();
			String mode = options.getProperty("battleField");
			switch (mode) {
			case "random":
				String numberOfShips = options.getProperty("numberOfShips");
				battleField = new RandomBattleField(Integer.parseInt(numberOfShips));
				break;
			case "file":
				battleField = new FileBattleField();
				break;
			default:
				throw new IllegalArgumentException("Unknown battlefield type: " + mode + "! Use the following: random, file");
			}
		}

		return battleField;
	}

}
