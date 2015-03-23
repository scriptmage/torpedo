package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class QuitCommand extends Command {

	private static final String COMMAND_NAME = "QUIT";
	
	public QuitCommand() {
		runnableOff();
		sendableOff();
	}

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			addResponse(new ErrorCommand("Unknown protocol"));
		}
		addResponse(new QuitCommand());
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
