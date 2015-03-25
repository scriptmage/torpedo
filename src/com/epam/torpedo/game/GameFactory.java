package com.epam.torpedo.game;

import com.epam.torpedo.Startable;
import com.epam.torpedo.game.types.LocalGame;
import com.epam.torpedo.game.types.SocketGame;

public class GameFactory {

	public static Startable createGame(String gameMode) {
		Startable result = null;
		switch (gameMode) {
		case "socket":
			result = new SocketGame();
			break;
		case "local":
			result = new LocalGame();
			break;
		default:
			throw new IllegalArgumentException("Unknown game mode: " + gameMode + "! Use the following: local, socket");
		}

		return result;
	}

}
