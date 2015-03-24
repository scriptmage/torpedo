package com.epam.torpedo.board;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.board.battlefields.FileBattleField;
import com.epam.torpedo.board.battlefields.RandomBattleField;
import com.epam.torpedo.options.Options;

public class BattleFieldFactory {

	public static BattleField getBattleField(Options options) {
		BattleField result = null;

		String mode = options.getProperty("battleField");
		switch (mode.toLowerCase()) {
		case "random":
			String numberOfShips = options.getProperty("numberOfShips");
			result = new RandomBattleField(Integer.parseInt(numberOfShips));
			break;
		case "file":
			result = new FileBattleField();
			break;
		default:
			throw new IllegalArgumentException("Unknown battlefield type: " + mode + "! Use the following: random, file");
		}

		return result;
	}
	
}
