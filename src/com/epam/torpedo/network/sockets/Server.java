package com.epam.torpedo.network.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.components.Connection;
import com.epam.torpedo.network.Network;

public class Server extends Network {

	public Server(Connection connection) {
		super(connection);
	}

	@Override
	public void openConnection() {
		System.out.println("Waiting for client");
		try {
			int portNumber = connection.getPortNumber();
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket client = serverSocket.accept();
			setSocket(client);
			setIOStreams();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Client connected");
		hello();
	}

}
