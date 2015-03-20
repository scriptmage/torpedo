package com.epam.torpedo.network.connection.sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class SocketWriter {

	private DataOutputStream outputStream;

	public void createStream(Socket socket) {
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void send(CommandQueue commands) {
		Iterator<Command> iterator = commands.iterator();
		while (iterator.hasNext()) {
			send(iterator.next());
		}
	}

	public void send(Command command) {
		try {
			System.out.println("Output: " + command.toString());
			outputStream.writeUTF(command.toString().trim());
			outputStream.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
