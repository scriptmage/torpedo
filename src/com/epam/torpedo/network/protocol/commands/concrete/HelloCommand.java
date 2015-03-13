package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.ResponseSet;
import com.epam.torpedo.network.protocol.responses.concrete.FireResponse;

public class HelloCommand extends Command {

	private static final String COMMAND_NAME = "HELLO";

	private BattleField battleField;
	private Hunter hunter;

	public HelloCommand(BattleField battleField, Hunter hunter) {
		this.battleField = battleField;
		this.hunter = hunter;
	}

	@Override
	public ResponseSet getResponse(String input) {
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}
		
		ResponseSet response = new ResponseSet();
		battleField.createBattleField();
		response.add(new FireResponse(hunter));
		return response;
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, battleField.getDimension());
	}

}
