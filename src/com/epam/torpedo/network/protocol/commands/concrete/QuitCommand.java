package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class QuitCommand extends Command {

	private static final String COMMAND_NAME = "QUIT";

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			responseQueue.add(new ErrorCommand("Unknown protocol"));
		}
		responseQueue.add(new QuitCommand());
		return responseQueue;
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
