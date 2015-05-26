package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class WinCommand extends Command {

	// protocol is YOU WON
	private static final String COMMAND_NAME = "YOU";
	
	public WinCommand() {
		runnableOff();
	}

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		addResponse(new QuitCommand());
		System.out.println("I won!");
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return String.format("%s WON", COMMAND_NAME);
	}
	
}
