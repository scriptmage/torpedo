package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.commands.Command;
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
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		CommandQueue response = new CommandQueue();
		response.add(new QuitCommand());
		return response;
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, message);
	}

}
