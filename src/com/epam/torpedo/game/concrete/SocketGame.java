package com.epam.torpedo.game.concrete;

import java.io.IOException;

import com.epam.torpedo.Startable;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.battlefield.BattleFieldFactory;
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
		SocketTalker socketTalker = new SocketTalker(connectionData);
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

				socketTalker.send(response);
				hasRunning = getRunnableState(response);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			socketTalker.close();
		}
	}

	private void beginServerGame(SocketTalker socketTalker) {
		BattleField battleField = BattleFieldFactory.createBattleField();
		battleField.createBattleField();
		socketTalker.send(new HelloCommand());
	}
	
	private boolean getRunnableState(CommandQueue response) {
		boolean hasRunnable = true;
		if(response.size() == 1) {
			Command command = response.get(0);
			hasRunnable = command.isRunnable();
		}
		return hasRunnable;
	}

}