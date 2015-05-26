package com.epam.torpedo.targets;

import java.util.Random;

import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.targets.ships.concrete.FreeShip;
import com.epam.torpedo.targets.ships.concrete.LShip;
import com.epam.torpedo.targets.ships.concrete.LineShip;
import com.epam.torpedo.targets.ships.concrete.TShip;

public class ShipFactory {

	private static final int MAX_LINESHIP_WIDTH = 4;
	private static final int NUMBER_OF_SHIPTYPES = 3;

	static public Ship getRandomShip() {
		Ship ship;
		Dimension dimensionOfBattleField = GameConfig.getDimension();
		Random random = new Random();
		Coordinate coordinate = Coordinate.getRandomCoordinate(dimensionOfBattleField.getWidth(), dimensionOfBattleField.getHeight());

		switch (random.nextInt(NUMBER_OF_SHIPTYPES)) {
		case 1:
			ship = new TShip();
			break;
		case 2:
			ship = new LShip();
			break;
		default:
			ship = new LineShip(random.nextInt(MAX_LINESHIP_WIDTH) + 1);
		}

		ship.setPosition(coordinate);
		return ship;
	}

	static public Ship getFreeShip(Shape shape) {
		return new FreeShip(shape);
	}

}
