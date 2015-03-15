package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class HitCommand extends Command {

	private static final String COMMAND_NAME = "HIT";

	@Override
	public CommandQueue getResponse(String input) {
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		
		CommandQueue response = new CommandQueue();
		response.add(new NullCommand());
		return response;
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
