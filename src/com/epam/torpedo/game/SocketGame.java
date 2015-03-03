package com.epam.torpedo.game;

import com.epam.torpedo.Game;
import com.epam.torpedo.components.Connection;

public class SocketGame extends Game {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void start() {
		battleField.createBattleField();
		if (connection.isServerConnection()) {
			startServerGame();
		} else {
			startClientGame();
		}
	}

	private void startClientGame() {
		
	}

	private void startServerGame() {
		
	}

}
