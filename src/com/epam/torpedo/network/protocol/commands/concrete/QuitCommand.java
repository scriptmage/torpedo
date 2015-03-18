package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class QuitCommand extends Command {

	private static final String COMMAND_NAME = "QUIT";

	@Override
	public CommandQueue getResponse(String input) {
		String command = getCommand(input);
		CommandQueue response = new CommandQueue();
		if (!command.equals(COMMAND_NAME)) {
			response.add(new ErrorCommand("Unknown protocol"));
		}
		response.add(new QuitCommand());
		return response;
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
