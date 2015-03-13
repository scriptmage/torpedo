package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.Response;
import com.epam.torpedo.network.protocol.responses.concrete.FireResponse;

public class FireCommand extends Command {

	private static final String COMMAND_NAME = "FIRE";
	
	private BattleField battleField;
	private Hunter hunter;

	public FireCommand(BattleField battleField, Hunter hunter) {
		this.battleField = battleField;
		this.hunter = hunter;
	}

	@Override
	public Response getResponse(String input) {
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}

		Response response = new FireResponse();
		if (battleField.shoot(hunter)) {
			
		} else {
			
		}
		return response;
	}

}
