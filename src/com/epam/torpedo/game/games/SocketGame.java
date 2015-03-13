package com.epam.torpedo.game.games;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Game;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.network.ReadWriteSocket;
import com.epam.torpedo.network.protocol.ProtocolFactory;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.Response;

public class SocketGame extends Game {

	private Connection connection;
	private Socket client;
	private ServerSocket serverSocket;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void start() {
		try {
			ReadWriteSocket rwSocket = new ReadWriteSocket();

			openConnection();
			rwSocket.setIOStreams(client);

			Command protocol = ProtocolFactory.getProtocol(battleField, hunter);
			if (connection.isServerConnection()) {
				rwSocket.sendCommand("HELLO", battleField.getDimension().toString());
			}

			while (client.isConnected()) {
				String input = rwSocket.readCommand();
				Response response = protocol.getResponse(input);
				response.execute();
			}

			closeConnection();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private void closeConnection() throws IOException {
		client.close();
		if (connection.isServerConnection()) {
			serverSocket.close();
		}
	}

	private void openConnection() throws IOException, UnknownHostException {
		int portNumber = connection.getPortNumber();
		if (connection.isServerConnection()) {
			serverSocket = new ServerSocket(portNumber);
			client = serverSocket.accept();
		} else {
			String hostName = connection.getHostName();
			client = new Socket(hostName, portNumber);
		}
	}

}