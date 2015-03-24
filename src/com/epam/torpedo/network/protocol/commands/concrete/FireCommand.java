package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.hunters.ConcretePositionHunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class FireCommand extends Command {

	private static final String COMMAND_NAME = "FIRE";

	private Coordinate coordinate;

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

		Hunter hunter = Config.getHunter();
		ConcretePositionHunter shooter = getShooter();
		BattleField battleField = Config.getBattleField();

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

	private ConcretePositionHunter getShooter() {
		ConcretePositionHunter shooter = new ConcretePositionHunter();
		shooter.setDimension(Config.getBattleFieldDimension());
		shooter.setPosition(getParams());
		return shooter;
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, coordinate);
	}

}
