package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.hunters.HunterFactory;
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
		Hunter hunter = HunterFactory.getHunter();
		Coordinate lastShot = hunter.getLastShot();
		if(lastShot != null) {
			hunter.setPosition(new Coordinate(lastShot.getX() + 1, lastShot.getY()));
			hunter.setPosition(new Coordinate(lastShot.getX() - 1, lastShot.getY()));
			hunter.setPosition(new Coordinate(lastShot.getX(), lastShot.getY() + 1));
			hunter.setPosition(new Coordinate(lastShot.getX(), lastShot.getY() - 1));
		}
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return COMMAND_NAME;
	}

}
