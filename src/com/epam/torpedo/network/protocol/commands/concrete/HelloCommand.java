package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class HelloCommand extends Command {

	private static final String COMMAND_NAME = "HELLO";

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		
		BattleField battleField = Config.getBattleField();
		battleField.createBattleField();
		
		Hunter hunter = Config.getHunter();
		addResponse(new FireCommand(hunter.nextShot()));
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, Config.getBattleFieldDimension());
	}

}
