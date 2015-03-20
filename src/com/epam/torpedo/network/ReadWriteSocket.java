package com.epam.torpedo.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.epam.torpedo.network.connection.Connection;
import com.epam.torpedo.network.connection.ConnectionData;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class ReadWriteSocket extends Connection {
	private DataInputStream reader;
	private DataOutputStream writer;
	
	public ReadWriteSocket(ConnectionData connection) {
		super(connection);
		try {
			reader = new DataInputStream(getInputStream());
			writer = new DataOutputStream(getOutputStream());
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

	public void send(CommandQueue commands) {
		Iterator<Command> iterator = commands.iterator();
		while (iterator.hasNext()) {
			this.send(iterator.next());
		}
	}

	public void send(Command command) {
		try {
			System.out.println("Output: " + command.toString());
			writer.writeUTF(command.toString().trim());
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}