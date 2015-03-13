package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.responses.ResponseSet;
import com.epam.torpedo.network.protocol.responses.concrete.FireResponse;
import com.epam.torpedo.network.protocol.responses.concrete.HitResponse;
import com.epam.torpedo.network.protocol.responses.concrete.MissResponse;
import com.epam.torpedo.network.protocol.responses.concrete.WinResponse;

public class FireCommand extends Command {

	private static final String COMMAND_NAME = "FIRE";

	private BattleField battleField;
	private Hunter hunter;

	public FireCommand(BattleField battleField, Hunter hunter) {
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
		if (battleField.shoot(hunter)) {
			if (battleField.isAliveShips()) {
				response.add(new HitResponse());
				// response.add(new SunkResponse());
			} else {
				response.add(new WinResponse());
			}
		} else {
			response.add(new MissResponse());
		}

		response.add(new FireResponse(hunter));
		return response;
	}

}
