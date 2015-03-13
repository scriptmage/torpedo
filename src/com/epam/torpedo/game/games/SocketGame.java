package com.epam.torpedo.game.games;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.Game;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.network.ReadWriteSocket;
import com.epam.torpedo.network.protocol.ProtocolFactory;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;
import com.epam.torpedo.network.protocol.commands.concrete.WinCommand;
import com.epam.torpedo.network.protocol.responses.ResponseSet;

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
			openConnection();
			ReadWriteSocket rwSocket = new ReadWriteSocket();
			rwSocket.setIOStreams(client);

			Command protocol = ProtocolFactory.getProtocol(battleField, hunter);
			if (connection.isServerConnection()) {
				rwSocket.sendCommand(new HelloCommand(battleField, hunter));
			}

			boolean running = true;
			while (client.isConnected() && running) 
			{
				String input = rwSocket.read();
				ResponseSet response = protocol.getResponse(input);
				
				if(response.size() > 0) {
					rwSocket.send(response);
				} else {
					running = false;
				}
				
				if(!battleField.isAliveShips()) {
					rwSocket.sendCommand(new WinCommand());
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
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