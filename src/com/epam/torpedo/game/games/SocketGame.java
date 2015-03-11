package com.epam.torpedo.game.games;

import java.io.IOException;

import com.epam.torpedo.Game;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.network.sockets.Client;
import com.epam.torpedo.network.sockets.Server;

public class SocketGame extends Game {

	private Connection connection;

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
		Client client = new Client(connection);
		client.setShooter(hunter);
		client.setBattleField(battleField);
		client.start();
	}

	private void startServerGame() {
		Server server = new Server(connection);
		server.setShooter(hunter);
		server.setBattleField(battleField);
		server.start();
	}

}
