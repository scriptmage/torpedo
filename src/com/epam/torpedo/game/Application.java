package com.epam.torpedo.game;

import com.epam.torpedo.components.Config;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.game.types.SocketGame;

public class Application {

	public static void main(String[] args) {
		System.out.println("BattleShip");
		Connection connection = createConnection(args);

		try {
			SocketGame game = (SocketGame) Config.getGame();
			game.setConnection(connection);
			game.start();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Game Over");

	}

	private static Connection createConnection(String[] args) {
		Connection connection = new Connection();
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
