package com.epam.torpedo.game;

import com.epam.torpedo.Startable;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.game.types.LocalGame;
import com.epam.torpedo.game.types.SocketGame;
import com.epam.torpedo.resolvers.Resolver;

public class GameFactory {

	public static Startable createGame() {
		Resolver resolver = Config.getResolver();
		String gameMode = resolver.get("game");

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
