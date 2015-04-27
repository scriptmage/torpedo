package com.epam.torpedo.network.connection.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import com.epam.torpedo.network.protocol.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class SocketWriter {

	private PrintWriter outputStream;

	public void createStream(Socket socket) {
		try {
			outputStream = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void send(CommandQueue commands) {
		Iterator<Command> iterator = commands.iterator();
		while (iterator.hasNext()) {
			Command command = iterator.next();
			if(command.isSendable()) {
				send(command);
			}
		}
	}

	public void send(Command command) {
		if(command.isSendable()) {
			System.out.println("Output: " + command.toString());
			outputStream.println(command.toString().trim());
		}
	}
}
