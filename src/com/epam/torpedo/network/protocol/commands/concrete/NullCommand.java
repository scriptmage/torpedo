package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class NullCommand extends Command {

	@Override
	public CommandQueue getResponse(String input) {
		return new CommandQueue();
	}

	@Override
	public String toString() {
		return "";
	}

}
