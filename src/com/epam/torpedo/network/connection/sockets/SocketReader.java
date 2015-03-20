package com.epam.torpedo.network.connection.sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketReader {

	private DataInputStream inputStream;

	public void createStream(Socket socket) {
		try {
			inputStream = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String read() {
		try {
			String input = inputStream.readUTF();
			System.err.println("Input: " + input);
			return input;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
