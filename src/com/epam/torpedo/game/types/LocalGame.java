package com.epam.torpedo.game.types;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Startable;
import com.epam.torpedo.components.Config;

public class LocalGame implements Startable {
	
	@Override
	public void start() {
		BattleField battleField = Config.getBattleField();
		Hunter hunter = Config.getHunter();
		
		battleField.createBattleField();
		while (battleField.isAliveShips()) {
			battleField.shoot(hunter);
		}
	}
	
}
