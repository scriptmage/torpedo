package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.battlefield.BattleFieldFactory;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.hunters.HunterFactory;
import com.epam.torpedo.network.protocol.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class HelloCommand extends Command {

	private static final String COMMAND_NAME = "HELLO";

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}

		BattleField battleField = BattleFieldFactory.createBattleField();
		battleField.createBattleField();

		Hunter hunter = HunterFactory.createHunter();
		addResponse(new FireCommand(hunter.nextShot()));
		return getResponseQueue();
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, GameConfig.getDimension());
	}

}
