package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.ResponseSet;
import com.epam.torpedo.network.protocol.responses.concrete.ErrorResponse;

public class SunkCommand extends Command {

	private static final String COMMAND_NAME = "SUNK";

	@Override
	public ResponseSet getResponse(String input) {
		String command = getCommand(input);
		ResponseSet response = new ResponseSet();

		if (!command.equals(COMMAND_NAME)) {
			response.add(new ErrorResponse("Protocol unknown: " + input));
		}
		
		return response;
	}

}
