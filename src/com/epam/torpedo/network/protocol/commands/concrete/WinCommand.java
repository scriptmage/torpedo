package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.ResponseSet;

public class WinCommand extends Command {

	// protocol is YOU WON
	private static final String COMMAND_NAME = "YOU";
	
	@Override
	public ResponseSet getResponse(String input) {
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		return new ResponseSet();
	}

	@Override
	public String toString() {
		return String.format("%s WON", COMMAND_NAME);
	}
}
