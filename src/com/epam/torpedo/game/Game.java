package com.epam.torpedo.game;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;

public class Game {
	private BattleField battleField;
	private Hunter hunter;

	public void setBattleFieldFillingStrategy(BattleField battleField) {
		this.battleField = battleField;
	}

	public void setHunterStrategy(Hunter hunter) {
		this.hunter = hunter;
	}

	public void start() {
		battleField.createBattleField();
		while (battleField.isAliveShips()) {
			battleField.shoot(hunter);
		}
	}
}
