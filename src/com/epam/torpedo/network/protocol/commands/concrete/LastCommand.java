package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class LastCommand extends Command {

	@Override
	public CommandQueue getResponse(String input) {
		CommandQueue response = new CommandQueue();
		response.add(new ErrorCommand("Unknown protocol"));
		return response;
	}

}
