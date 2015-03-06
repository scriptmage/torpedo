package com.epam.torpedo.game;

import java.io.File;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Protocol;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.board.battlefields.FileBattleField;
import com.epam.torpedo.game.hunters.RandomHunter;
import com.epam.torpedo.protocols.FirstProtocol;

public class Application {

	private static final String SHIPS_DATA_FILE = "ships.dat";
	private static final int BATTLEFIELD_WIDTH = 20;
	private static final int BATTLEFIELD_HEIGHT = 20;

	public static void main(String[] args) {
		System.out.println("BattleShip");
		Connection connection = createConnection(args);

		try {
			Hunter hunter = new RandomHunter();

			File dataOfShips = new File(SHIPS_DATA_FILE);
			Dimension dimensionOfBattleField = new Dimension(BATTLEFIELD_WIDTH, BATTLEFIELD_HEIGHT);
			BattleField battleField = new FileBattleField(dataOfShips, dimensionOfBattleField);

			SocketGame game = new SocketGame();
			FirstProtocol protocol = new FirstProtocol();
			protocol.setBattlefield(battleField);
			
			game.setBattleFieldFillingStrategy(battleField);
			game.setHunterStrategy(hunter);
			game.setConnection(connection);
			game.setProtocol(protocol);
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
