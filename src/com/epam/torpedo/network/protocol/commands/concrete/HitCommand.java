package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.ResponseSet;

public class HitCommand extends Command {

	private static final String COMMAND_NAME = "HIT";
	
	@Override
	public ResponseSet getResponse(String input) {
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		return new ResponseSet();
	}

}
