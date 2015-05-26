package com.epam.torpedo.network.connection.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketReader {

	private BufferedReader inputStream;

	public void createStream(Socket socket) {
		try {
			inputStream =  new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String read() {
		try {
			String input = inputStream.readLine();
//			System.out.println("<==  " + input);
			return input;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
