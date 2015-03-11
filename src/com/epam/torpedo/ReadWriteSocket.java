package com.epam.torpedo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ReadWriteSocket {
	private DataInputStream reader;
	private DataOutputStream writer;
	private Socket client;

	public void setSocket(Socket client) {
		this.client = client;
	}

	public void setIOStreams() {
		try {
			reader = new DataInputStream(client.getInputStream());
			writer = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readCommand() {
		try {
			String input =reader.readUTF();
			System.out.println("Input: " + input);
			return input;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendCommand(String command) {
		if (command.trim().length() > 0) {
			try {
				writer.writeUTF(command);
				System.out.println("Output: " + command);
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
		return client.isConnected();
	}
	
	public void closeConnection() {
		try {
			client.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
