package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class WinCommand extends Command {

	// protocol is YOU WON
	private static final String COMMAND_NAME = "YOU";

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		responseQueue.add(new QuitCommand());
		return responseQueue;
	}

	@Override
	public String toString() {
		return String.format("%s WON", COMMAND_NAME);
	}
	
}
