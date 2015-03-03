package com.epam.torpedo.game;

import java.io.File;
import java.io.IOException;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.board.battlefields.FileBattleField;
import com.epam.torpedo.game.hunters.RandomHunter;

public class Application {

	private static final int MAXIMUM_PORT_NUMBER = 65535;
	private static final int MINIMUM_PORT_NUMBER = 1024;
	private static final String SHIPS_DATA_FILE = "ships.dat";
	private static final int BATTLEFIELD_WIDTH = 20;
	private static final int BATTLEFIELD_HEIGHT = 20;

	public static void main(String[] args) {
		int portNumber = 3235;
		String hostName = "127.0.0.1";

		System.out.println("Torpedo v0.3");
		switch (args.length) {
		case 1:
			try {
				portNumber = Integer.parseInt(args[0]);
				validatePortNumber(portNumber);
			} catch (NumberFormatException e) {
				System.out.println("This isn't a number: " + args[0]
						+ ". Please, give me a number.");
				System.exit(-1);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		case 0:
			System.out.println("Starting server on " + portNumber + " port");
			break;
		case 2:
			try {
				hostName = args[0];
				portNumber = Integer.parseInt(args[1]);
				validatePortNumber(portNumber);
			} catch (NumberFormatException e) {
				System.out.println("This isn't a number: " + args[0]
						+ ". Please, give me a number.");
				System.exit(-1);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.exit(-1);
			}
			break;
		default:
			System.out.println("Use server mode: torpedo [port]");
			System.out
					.println("Use client mode: torpedo server_hostname [port]");
			System.exit(-1);
		}

		System.out.println("Connect to " + hostName + " on " + portNumber
				+ " port");

		try {
			Game game = new Game();
			Dimension dimensionOfBattleField = new Dimension(BATTLEFIELD_WIDTH,
					BATTLEFIELD_HEIGHT);
			FileBattleField battleField = new FileBattleField(
					dimensionOfBattleField);
			Hunter hunter = new RandomHunter();
			int numberOfShips = battleField.parser(new File(SHIPS_DATA_FILE));

			game.setBattleFieldFillingStrategy(battleField);
			game.setHunterStrategy(hunter);
			game.start(numberOfShips);
		} catch (RuntimeException | IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Game Over");

	}

	private static void validatePortNumber(int portNumber) {
		if (!isValidPortNumber(portNumber)) {
			throw new IllegalArgumentException("Port number should be between "
					+ MINIMUM_PORT_NUMBER + " and " + MAXIMUM_PORT_NUMBER);
		}
	}

	private static boolean isValidPortNumber(int portNumber) {
		return portNumber > MINIMUM_PORT_NUMBER
				&& portNumber < MAXIMUM_PORT_NUMBER;
	}

}
