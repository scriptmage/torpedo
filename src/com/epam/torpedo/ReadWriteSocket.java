package com.epam.torpedo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ReadWriteSocket {
	private DataInputStream reader;
	private DataOutputStream writer;
	private Socket socket;

	public void setSocket(Socket socket) {
		this.socket = socket;
		setIOStreams();
	}

	private void setIOStreams() {
		try {
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readCommand() {
		try {
			String input = reader.readUTF();
			return input.toUpperCase();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendCommand(String command) {
		if (command.trim().length() > 0) {
			try {
				writer.writeUTF(command);
				writer.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void sendCommand(String command, String param) {
		if (command.trim().length() == 0) {
			throw new IllegalArgumentException("Command should not be empty");
		}

		if (param.trim().length() == 0) {
			throw new IllegalArgumentException("Param should not be empty");
		}

		sendCommand(command + " " + param);
	}

	public boolean isConnected() {
		return ((Socket) socket).isConnected();
	}
	
	public void closeConnection() {
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
