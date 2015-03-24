package com.epam.torpedo.game;

import com.epam.torpedo.Startable;
import com.epam.torpedo.game.types.LocalGame;
import com.epam.torpedo.game.types.SocketGame;

public class GameFactory {

	public static Startable createGame(GameMode gameMode) {
		Startable result = null;

		String mode = gameMode.getGameMode();
		switch (mode.toLowerCase()) {
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
