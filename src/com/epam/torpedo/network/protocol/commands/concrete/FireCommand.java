package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class FireCommand extends Command {

	private static final String COMMAND_NAME = "FIRE";

	private BattleField battleField;
	private Hunter hunter;
	private Coordinate coordinate;

	public FireCommand(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public FireCommand(BattleField battleField, Hunter hunter) {
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
		if (battleField.shoot(hunter)) {
			if (battleField.isAliveShips()) {
				
				System.out.println(String.format("Params: X[%s] Y[%s]", getParams()[0], getParams()[1]));
				response.add(new HitCommand());
				// response.add(new SunkResponse());
				
			} else {
				response.add(new WinCommand());
			}
		} else {
			response.add(new MissCommand());
		}
		
		response.add(new FireCommand(hunter.nextShot()));
		return response;
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, coordinate);
	}

}
