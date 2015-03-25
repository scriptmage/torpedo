package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.Ship;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.battlefield.BattleFieldFactory;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.hunters.HunterFactory;
import com.epam.torpedo.hunters.concrete.ConcretePositionHunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class FireCommand extends Command {

	private static final String COMMAND_NAME = "FIRE";

	private Coordinate coordinate;
	private BattleField battleField;
	private ConcretePositionHunter shooter = HunterFactory.createShooter();

	public FireCommand() {

	}

	public FireCommand(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public CommandQueue getResponse(String input) {
		initCommand(input);
		if (!isCommand(COMMAND_NAME)) {
			return successor.getResponse(input);
		}

		shooter.setPosition(getParams());
		battleField = BattleFieldFactory.createBattleField();
		Hunter hunter = HunterFactory.createHunter();

		if (battleField.shoot(shooter)) {

			if (battleField.isAliveShips()) {
				Command command = new HitCommand();
				Ship ship = battleField.getShip(shooter.getPosition());

				if (!ship.isAlive()) {
					command = new SunkCommand();
				}

				addResponse(command);
				addResponse(new FireCommand(hunter.nextShot()));
			} else {
				addResponse(new WinCommand());
			}

		} else {
			addResponse(new MissCommand());
			addResponse(new FireCommand(hunter.nextShot()));
		}

		return getResponseQueue();
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, coordinate);
	}

}
