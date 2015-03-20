package com.epam.torpedo.network.connection.sockets;

import com.epam.torpedo.network.ConnectionData;
import com.epam.torpedo.network.connection.Connection;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class SocketTalker extends Connection {
	private SocketReader reader;
	private SocketWriter writer;

	public SocketTalker(ConnectionData connectionData) {
		super(connectionData);
		reader = new SocketReader();
		writer = new SocketWriter();
	}
	
	public void createIOStreams() {
		reader.createStream(client);
		writer.createStream(client);
	}

	public String read() {
		return reader.read();
	}

	public void send(CommandQueue commands) {
		writer.send(commands);
	}

	public void send(Command command) {
		writer.send(command);
	}
	
}