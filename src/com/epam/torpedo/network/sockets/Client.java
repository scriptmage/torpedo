package com.epam.torpedo.network.sockets;

import java.io.IOException;
import java.net.Socket;

import com.epam.torpedo.components.Connection;
import com.epam.torpedo.network.Network;

public class Client extends Network {

	public Client(Connection connection) {
		super(connection);
	}

	@Override
	public void openConnection() {
		try {
			int portNumber = connection.getPortNumber();
			String hostName = connection.getHostName();
			Socket client = new Socket(hostName, portNumber);
			setSocket(client);
			setIOStreams();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Connected to " + connection.getHostName() + ":" + connection.getPortNumber());
	}

}
