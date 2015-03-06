package com.epam.torpedo.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.Game;
import com.epam.torpedo.Protocol;
import com.epam.torpedo.components.Connection;

public class SocketGame extends Game {

	private Connection connection;
	private Protocol protocol;

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
		}
	}

	private void startClientGame() throws IOException {

	}

	private void startServerGame() throws IOException {
		battleField.createBattleField();
		System.out.println("Starting server game");
		ServerSocket serverSocket = new ServerSocket(connection.getPortNumber());
		
		System.out.println("Waiting for client");
		Socket client = serverSocket.accept();

		System.out.println("Client connected");
		DataInputStream reader = new DataInputStream(client.getInputStream());
		DataOutputStream writer = new DataOutputStream(client.getOutputStream());
		
		while (client.isConnected()) {
			String input = reader.readUTF();
			String answer = protocol.processInput(input);
			writer.writeUTF(answer);
		}
		
		serverSocket.close();
	}

}
