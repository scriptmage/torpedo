package com.epam.torpedo.game;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.Game;
import com.epam.torpedo.components.Connection;

public class SocketGame extends Game {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void start() {
		battleField.createBattleField();
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
		System.out.println("Starting server game");
		ServerSocket serverSocket = new ServerSocket(connection.getPortNumber());
		System.out.println("Waiting for client");
		Socket client = serverSocket.accept();
		
		if(client.isConnected()) {
			System.out.println("Client connected");
			
			DataInputStream reader = new DataInputStream(client.getInputStream());
			// PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
			
			while(client.isConnected()) {
				System.out.println("Your message was: " + reader.readUTF());
			}	
		}
	}

}
