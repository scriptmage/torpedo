package com.epam.torpedo.network;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Connection;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.hunters.ConcretePositionHunter;

public abstract class Network extends Protocol {

	private Hunter shooter;
	private BattleField battleField;
	private ConcretePositionHunter hunter;
	private Dimension dimensionOfBattleField;
	
	protected Connection connection;

	public void setShooter(Hunter shooter) {
		this.shooter = shooter;
	}

	public void hello() {
		sendCommand("HELLO", dimensionOfBattleField.toString());
	}
	
	public Network(Connection connection) {
		this.connection = connection;
	}

	public void setBattleField(BattleField battleField) {
		this.battleField = battleField;
	}

	public void start() {
		createBoard();

		openConnection();

		boolean running = true;
		try {
			while (isConnected() && running) {
				String command = readCommand();
				switch (command) {
				case "FIRE":
					hunter.setPosition(getCoordinate());
					if (battleField.shoot(hunter)) {
						hit();
					} else {
						miss();
					}
					fire(shooter.nextShot());
					break;
				case "HIT":
					break;
				case "MISS":
					break;
				case "YOU":
					System.out.println("I won ;)");
					System.exit(0);
					break;
				case "ERROR":
					System.out.println(getErrorMessage());
					System.exit(-1);
					break;
				default:
					error("Unknown protocol");
					running = false;
				}

				if (!battleField.isAliveShips()) {
					win();
					running = false;
				}
			}
		} catch (NumberFormatException e) {
			error("Invalid FIRE params");
		} catch (IllegalArgumentException e) {
			error("Invalid FIRE position");
		}

		closeConnection();
	}

	private void createBoard() {
		battleField.createBattleField();
		dimensionOfBattleField = battleField.getDimension();
		hunter = new ConcretePositionHunter();
		hunter.setDimension(dimensionOfBattleField);
		shooter.setDimension(dimensionOfBattleField);
	}

	abstract public void openConnection();
}
