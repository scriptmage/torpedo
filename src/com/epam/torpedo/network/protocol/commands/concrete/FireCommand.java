package com.epam.torpedo.network.protocol.commands.concrete;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.game.hunters.ConcretePositionHunter;
import com.epam.torpedo.network.protocol.commands.Command;
import com.epam.torpedo.network.protocol.commands.CommandQueue;

public class FireCommand extends Command {

	private static final String COMMAND_NAME = "FIRE";

	private BattleField battleField;
	private Hunter hunter;
	private Coordinate coordinate;
	private ConcretePositionHunter shooter = new ConcretePositionHunter();

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

		Command response;
		CommandQueue responseQueue = new CommandQueue();
		
		Object[] params = getParams();
		int x = Integer.parseInt((String) params[0]);
		int y = Integer.parseInt((String) params[1]);
		
		shooter.setDimension(battleField.getDimension());
		shooter.setPosition(x, y);
		if (battleField.shoot(shooter)) {
			if (battleField.isAliveShips()) {

				Ship ship = battleField.getShip(x, y);
				if(ship.isAlive()) {
					response = new HitCommand();
				} else {
					response = new SunkCommand();
				}

			} else {
				response = new WinCommand();
			}
			
		} else {
			response = new MissCommand();
		}
		
		responseQueue.add(response);
		responseQueue.add(new FireCommand(hunter.nextShot()));
		return responseQueue;
	}

	@Override
	public String toString() {
		return String.format("%s %s", COMMAND_NAME, coordinate);
	}

}
