package com.epam.torpedo.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import com.epam.torpedo.Game;
import com.epam.torpedo.Protocol;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.game.hunters.ConcretePositionHunter;
import com.epam.torpedo.game.hunters.RandomHunter;

public class SocketGame extends Game {

	private Connection connection;
	private Protocol protocol;
	private DataOutputStream writer;

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void start() {
		try {
			if (connection.isServerConnection()) {
				startServerGame();
			} else {
				startClientGame();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void startClientGame() throws IOException, InterruptedException {
		System.out.println("Starting client game");
		Socket client = new Socket(connection.getHostName(),
				connection.getPortNumber());

		DataInputStream reader = new DataInputStream(client.getInputStream());
		writer = new DataOutputStream(client.getOutputStream());

		ConcretePositionHunter hunter = new ConcretePositionHunter();
		hunter.setDimension(battleField.getDimension());

		RandomHunter shooter = new RandomHunter();
		shooter.setDimension(battleField.getDimension());

		String answer = "ERROR Unknown protocol";
		Boolean running = true;
		while (client.isConnected() && running) {
			String input = reader.readUTF();
			System.out.println("Input: " + input);
			StringTokenizer commandParser = new StringTokenizer(
					input.toUpperCase(), " ");
			try {

				String command = commandParser.nextToken();
				if (command.equals("HELLO")) {
					int widthOfBattleField = Integer.parseInt(commandParser
							.nextToken());
					int heightOfBattleField = Integer.parseInt(commandParser
							.nextToken());
					battleField.setDimension(widthOfBattleField,
							heightOfBattleField);
					battleField.createBattleField();
					answer = "FIRE " + shooter.nextShot();
				} else if (command.equals("FIRE")) {
					int x = Integer.parseInt(commandParser.nextToken());
					int y = Integer.parseInt(commandParser.nextToken());

					hunter.setPosition(x, y);
					if (battleField.shoot(hunter)) {
						sendCommand("HIT");
					} else {
						sendCommand("MISS");
					}
					answer = "FIRE " + shooter.nextShot();
				} else if (command.equals("HIT")) {

				} else if (command.equals("YOU")) {
					System.out.println("I won ;)");
					System.exit(0);
				}
			} catch (NumberFormatException e) {
				answer = "ERROR Invalid FIRE params";
				running = false;
			} catch (IllegalArgumentException e) {
				answer = "ERROR Invalid FIRE position";
				running = false;
			}

			if (!battleField.isAliveShips()) {
				answer = "YOU WON";
				running = false;
			}

			sendCommand(answer);
			System.out.println("Answer: " + answer);
			System.out.println();
			Thread.sleep(2500);
		}

		client.close();
	}

	private void startServerGame() throws IOException, InterruptedException {
		battleField.createBattleField();
		System.out.println("Starting server game");
		ServerSocket serverSocket = new ServerSocket(connection.getPortNumber());

		System.out.println("Waiting for client");
		Socket client = serverSocket.accept();

		System.out.println("Client connected");
		DataInputStream reader = new DataInputStream(client.getInputStream());
		writer = new DataOutputStream(client.getOutputStream());

		ConcretePositionHunter hunter = new ConcretePositionHunter();
		hunter.setDimension(battleField.getDimension());

		RandomHunter shooter = new RandomHunter();
		shooter.setDimension(battleField.getDimension());

		sendCommand("HELLO " + battleField);

		String answer = "ERROR Unknown protocol";
		boolean running = true;
		while (client.isConnected() && running) {
			String input = reader.readUTF();
			System.out.println("Input: " + input);
			StringTokenizer commandParser = new StringTokenizer(
					input.toUpperCase(), " ");

			String command = commandParser.nextToken();
			try {
				if (command.equals("FIRE")) {
					int x = Integer.parseInt(commandParser.nextToken());
					int y = Integer.parseInt(commandParser.nextToken());

					hunter.setPosition(x, y);
					if (battleField.shoot(hunter)) {
						sendCommand("HIT");
					} else {
						sendCommand("MISS");
					}
					answer = "FIRE " + shooter.nextShot();
				} else if (command.equals("HIT")) {

				} else if (command.equals("YOU")) {
					System.out.println("I won ;)");
					System.exit(0);
				}
			} catch (NumberFormatException e) {
				answer = "ERROR Invalid FIRE params";
				running = false;
			} catch (IllegalArgumentException e) {
				answer = "ERROR Invalid FIRE position";
				running = false;
			}

			if (!battleField.isAliveShips()) {
				answer = "YOU WON";
				running = false;
			}

			sendCommand(answer);
			System.out.println("Answer: " + answer);
			System.out.println();
			Thread.sleep(2500);
		}

		serverSocket.close();
	}

	private void sendCommand(String command) throws IOException {
		writer.writeUTF(command);
		writer.flush();
		System.out.println(command);
	}

	private void sendCommand(String command, String param) throws IOException {
		sendCommand(command + " " + param);
	}

}
