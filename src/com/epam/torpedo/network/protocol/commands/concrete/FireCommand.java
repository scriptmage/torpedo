package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.game.hunters.ConcretePositionHunter;
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
		String command = getCommand(input);
		if (!command.equals(COMMAND_NAME)) {
			return successor.getResponse(input);
		}

		BattleField battleField = Config.getBattleField();
		Hunter hunter = Config.getHunter();
		ConcretePositionHunter shooter = getShooter();
		
		CommandQueue responseQueue = new CommandQueue();
		if (battleField.shoot(shooter)) {

			if (battleField.isAliveShips()) {
				Ship ship = battleField.getShip(shooter.getPosition());
				if (ship.isAlive()) {
					responseQueue.add(new HitCommand());
					responseQueue.add(new FireCommand(hunter.nextShot()));
				} else {
					responseQueue.add(new SunkCommand());
					responseQueue.add(new FireCommand(hunter.nextShot()));
				}

			} else {
				responseQueue.add(new WinCommand());
			}

		} else {
			responseQueue.add(new MissCommand());
			responseQueue.add(new FireCommand(hunter.nextShot()));
		}

		return responseQueue;
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
