package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.network.protocol.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class SunkCommand extends Command {

	private static final String COMMAND_NAME = "SUNK";

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		// TODO az elsüllyedt hajó körüli területet hozzáadni valahogy azokhoz a
		// pontokhoz, amiket már kilőtt a hunter, hiszen ide már nem szabadna
		// lőni
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
