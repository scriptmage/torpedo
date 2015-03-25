package com.epam.torpedo.board;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.board.battlefields.FileBattleField;
import com.epam.torpedo.board.battlefields.RandomBattleField;
import com.epam.torpedo.options.Options;
import com.epam.torpedo.options.datasources.PropertiesOptions;

public class BattleFieldFactory {

	private static BattleField battleField;

	public static BattleField createBattleField() {

		if (battleField == null) {
			Options options = new PropertiesOptions();
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
