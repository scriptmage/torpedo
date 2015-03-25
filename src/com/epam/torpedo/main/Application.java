package com.epam.torpedo.main;

import com.epam.torpedo.Startable;
import com.epam.torpedo.game.GameFactory;
import com.epam.torpedo.game.types.SocketGame;
import com.epam.torpedo.network.ConnectionData;
import com.epam.torpedo.resolvers.GameModeResolver;
import com.epam.torpedo.resolvers.datasources.PropertyReader;

public class Application {
	
	public static final String CONFIG_FILE = "config/config.properties";
	
	public static void main(String[] args) {
		System.out.println("BattleShip");
		ConnectionData connectionData = createConnection(args);

		try {
			GameModeResolver gameModeResolver = new PropertyReader("game");
			String gameMode = gameModeResolver.getGameMode();
			
			Startable game = GameFactory.createGame(gameMode);
			((SocketGame) game).setConnection(connectionData);
			game.start();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Game Over");
	}

	private static ConnectionData createConnection(String[] args) {
		ConnectionData connection = new ConnectionData();
		switch (args.length) {
		case 0:
			System.out.println(String.format("Starting server on %d port", connection.getPortNumber()));
			break;
		case 1:
			connection.setPortNumber(args[0]);
			System.out.println(String.format("Starting server on %d port", connection.getPortNumber()));
			break;
		case 2:
			connection.setHostName(args[0]);
			connection.setPortNumber(args[1]);
			System.out.println(String.format("Connect to %s on %d port", connection.getHostName(), connection.getPortNumber()));
			break;
		default:
			System.err.println("Please, give me a port number for server mode, or hostname and port number for client mode");
			System.err.println("Server mode: torpedo <port number>");
			System.err.println("Client mode: torpedo <hostname> <port number>");
			System.exit(1);
		}
		return connection;
	}

}
