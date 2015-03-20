package com.epam.torpedo.game.types;

import java.io.IOException;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Startable;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.network.ConnectionData;
import com.epam.torpedo.network.connection.sockets.SocketTalker;
import com.epam.torpedo.network.protocol.ProtocolFactory;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;
import com.epam.torpedo.network.protocol.commands.concrete.HelloCommand;

public class SocketGame implements Startable {

	private ConnectionData connectionData;

	public void setConnection(ConnectionData connectionData) {
		this.connectionData = connectionData;
	}

	@Override
	public void start() {
		SocketTalker socketTalker = getSocketTalker();
		Command protocol = ProtocolFactory.getProtocol();

		try {
			socketTalker.open();
			socketTalker.createIOStreams();

			if (connectionData.isServerConnection()) {
				beginServerGame(socketTalker);
			}

			boolean hasRunning = true;
			while (socketTalker.isConnected() && hasRunning) {
				String input = socketTalker.read();
				CommandQueue response = protocol.getResponse(input);

				switch (response.toString()) {
				case "YOU WON":
					socketTalker.send(response);
				case "QUIT":
					hasRunning = false;
					break;
				default:
					socketTalker.send(response);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			socketTalker.close();
		}
	}

	private SocketTalker getSocketTalker() {
		SocketTalker socketTalker = new SocketTalker(connectionData);
		return socketTalker;
	}

	private void beginServerGame(SocketTalker socketTalker) {
		BattleField battleField = Config.getBattleField();
		battleField.createBattleField();
		socketTalker.send(new HelloCommand());
	}

}