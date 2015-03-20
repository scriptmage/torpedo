package com.epam.torpedo.network.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.torpedo.network.ConnectionData;

public class Connection {

	protected Socket client;
	private ServerSocket serverSocket;
	private ConnectionData connectionData;

	public Connection(ConnectionData connection) {
		this.connectionData = connection;
	}

	public boolean isConnected() {
		return client.isConnected();
	}

	public void open() throws UnknownHostException, IOException {
		int portNumber = connectionData.getPortNumber();
		if (connectionData.isServerConnection()) {
			serverSocket = new ServerSocket(portNumber);
			client = serverSocket.accept();
		} else {
			String hostName = connectionData.getHostName();
			client = new Socket(hostName, portNumber);
		}
	}

	public void close() {
		try {
			client.close();
			if (connectionData.isServerConnection()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
