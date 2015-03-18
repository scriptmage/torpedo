package com.epam.torpedo.game.types;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Startable;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.network.ReadWriteSocket;
import com.epam.torpedo.network.protocol.ProtocolFactory;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;

public class SocketGame implements Startable {

	private Connection connection;
	private Socket client;
	private ServerSocket serverSocket;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void start() {
		try {
			openConnection();
			ReadWriteSocket rwSocket = getIOStreams();
			Command protocol = ProtocolFactory.getProtocol();

			if (connection.isServerConnection()) {
				beginServerGame(rwSocket);
			}

			boolean hasRunning = true;
			while (client.isConnected() && hasRunning) {
				String input = rwSocket.read();
				CommandQueue commands = protocol.getResponse(input);

				switch (commands.toString()) {
				case "YOU WON":
					rwSocket.send(commands);
				case "QUIT":
					hasRunning = false;
					break;
				default:
					rwSocket.send(commands);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
	}

	private void beginServerGame(ReadWriteSocket rwSocket) {
		BattleField battleField = Config.getBattleField();
		battleField.createBattleField();
		rwSocket.sendCommand(new HelloCommand());
	}

	private ReadWriteSocket getIOStreams() {
		ReadWriteSocket rwSocket = new ReadWriteSocket();
		rwSocket.setIOStreams(client);
		return rwSocket;
	}

	private void closeConnection() {
		try {
			client.close();
			if (connection.isServerConnection()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void openConnection() throws IOException {
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