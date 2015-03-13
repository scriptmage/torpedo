package com.epam.torpedo.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.Response;
import com.epam.torpedo.network.protocol.responses.ResponseSet;

public class ReadWriteSocket {
	private DataInputStream reader;
	private DataOutputStream writer;

	public void setIOStreams(Socket client) {
		try {
			reader = new DataInputStream(client.getInputStream());
			writer = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String read() {
		try {
			String input = reader.readUTF();
			System.out.println("Input: " + input);
			return input;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void send(ResponseSet responseSet) {
		Iterator<Response> iterator = responseSet.iterator();
		while(iterator.hasNext()) {
			sendCommand(iterator.next().get());
		}
	}
	
	public void sendCommand(Command command) {
		sendCommand(command.toString());
	}
	
	private void sendCommand(String command) {
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

//	private void sendCommand(String command, String param) {
//		if (command.trim().length() == 0) {
//			throw new IllegalArgumentException("Command should not be empty");
//		}
//
//		if (param.trim().length() == 0) {
//			throw new IllegalArgumentException("Param should not be empty");
//		}
//
//		sendCommand(command + " " + param);
//	}

}