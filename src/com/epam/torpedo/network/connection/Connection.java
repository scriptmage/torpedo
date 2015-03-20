package com.epam.torpedo.network.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {

	private Socket client;
	private ServerSocket serverSocket;
	private ConnectionData connectionData;

	public Connection(ConnectionData connection) {
		this.connectionData = connection;
	}
	
	public InputStream getInputStream() throws IOException {
		return client.getInputStream();
	}
	
	public OutputStream getOutputStream() throws IOException {
		return client.getOutputStream();
	}

	public boolean isConnected() {
		return client.isConnected();
	}
	
	public void open() throws IOException {
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
