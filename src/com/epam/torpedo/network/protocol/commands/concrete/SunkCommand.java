package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class SunkCommand extends Command {

	private static final String COMMAND_NAME = "SUNK";

	@Override
	public CommandQueue getResponse(String input) {
		String command = getCommand(input);
		CommandQueue response = new CommandQueue();

		if (!command.equals(COMMAND_NAME)) {
			// azért van, mert ez az utolsó a láncban
			response.add(new ErrorCommand("Unknown protocol"));
		}

		response.add(new NullCommand());
		return response;
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
