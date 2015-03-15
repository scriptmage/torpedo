package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class HelloCommand extends Command {

	private static final String COMMAND_NAME = "HELLO";

	private BattleField battleField;
	private Hunter hunter;

	public HelloCommand(BattleField battleField, Hunter hunter) {
		this.battleField = battleField;
		this.hunter = hunter;
	}

	@Override
	public CommandQueue getResponse(String input) {
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		
		CommandQueue response = new CommandQueue();
		battleField.createBattleField();
		response.add(new FireCommand(hunter.nextShot()));
		return response;
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, battleField.getDimension());
	}

}
