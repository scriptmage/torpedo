package com.epam.torpedo.game.games;

import com.epam.torpedo.Game;

public class LocalGame extends Game {
	
	@Override
	public void start() {
		battleField.createBattleField();
		while (battleField.isAliveShips()) {
			battleField.shoot(hunter);
		}
	}
	
}
