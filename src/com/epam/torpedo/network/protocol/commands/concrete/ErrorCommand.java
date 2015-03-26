package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class ErrorCommand extends Command {

	private static final String COMMAND_NAME = "ERROR";
	private String message;

	public ErrorCommand() {
		this.message = "";
	}

	public ErrorCommand(String message) {
		this.message = message;
	}

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		addResponse(new QuitCommand());
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, message);
	}

}
