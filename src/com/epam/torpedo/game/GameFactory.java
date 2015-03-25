package com.epam.torpedo.game;

import com.epam.torpedo.Startable;
import com.epam.torpedo.game.types.LocalGame;
import com.epam.torpedo.game.types.SocketGame;
import com.epam.torpedo.options.Options;

public class GameFactory {

	public static Startable createGame(Options gameMode) {
		Startable result = null;

		String mode = gameMode.getProperty("game");
		switch (mode) {
		case "socket":
			result = new SocketGame();
			break;
		case "local":
			result = new LocalGame();
			break;
		default:
			throw new IllegalArgumentException("Unknown game mode: " + mode + "! Use the following: local, socket");
		}

		return result;
	}

}
