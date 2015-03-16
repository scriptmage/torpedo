package com.epam.torpedo.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

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
			System.err.println("Input: " + input);
			return input;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void send(CommandQueue commandSet) {
		Collection<Command> commands = commandSet.get();
		Iterator<Command> iterator = commands.iterator();
		while(iterator.hasNext()) {
			sendCommand(iterator.next());
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

}