package com.epam.torpedo.game.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.Startable;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.battlefield.BattleFieldFactory;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.hunters.HunterFactory;

public class LocalGame implements Startable {
	
	@Override
	public void start() {
		BattleField battleField = BattleFieldFactory.getBattleField();
		Hunter hunter = HunterFactory.getHunter();
		
		battleField.createBattleField(GameConfig.getDimension());
		while (battleField.isAliveShips()) {
			battleField.shoot(hunter);
		}
	}
	
}
