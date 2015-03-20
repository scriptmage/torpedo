package com.epam.torpedo.game.types;

import java.io.IOException;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Startable;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.network.ReadWriteSocket;
import com.epam.torpedo.network.connection.ConnectionData;
import com.epam.torpedo.network.protocol.ProtocolFactory;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;

public class SocketGame implements Startable {

	private ConnectionData connectionData;

	public void setConnection(ConnectionData connection) {
		this.connectionData = connection;
	}

	@Override
	public void start() {
		ReadWriteSocket rwSocket = getReadWriteSocket();
		Command protocol = ProtocolFactory.getProtocol();

		try {
			rwSocket.open();

			if (connectionData.isServerConnection()) {
				beginServerGame(rwSocket);
			}

			boolean hasRunning = true;
			while (rwSocket.isConnected() && hasRunning) {
				String input = rwSocket.read();
				CommandQueue response = protocol.getResponse(input);

				switch (response.toString()) {
				case "YOU WON":
					rwSocket.send(response);
				case "QUIT":
					hasRunning = false;
					break;
				default:
					rwSocket.send(response);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			rwSocket.close();
		}
	}

	private void beginServerGame(ReadWriteSocket rwSocket) {
		BattleField battleField = Config.getBattleField();
		battleField.createBattleField();
		rwSocket.send(new HelloCommand());
	}

	private ReadWriteSocket getReadWriteSocket() {
		return new ReadWriteSocket(connectionData);
	}

}