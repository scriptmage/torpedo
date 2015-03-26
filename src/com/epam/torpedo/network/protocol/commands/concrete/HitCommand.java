package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class HitCommand extends Command {

	private static final String COMMAND_NAME = "HIT";

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
