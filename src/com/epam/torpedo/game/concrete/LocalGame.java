package com.epam.torpedo.game.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.Startable;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.battlefield.BattleFieldFactory;
import com.epam.torpedo.hunters.HunterFactory;

public class LocalGame implements Startable {
	
	@Override
	public void start() {
		BattleField battleField = BattleFieldFactory.createBattleField();
		Hunter hunter = HunterFactory.createHunter();
		
		battleField.createBattleField();
		while (battleField.isAliveShips()) {
			battleField.shoot(hunter);
		}
	}
	
}
