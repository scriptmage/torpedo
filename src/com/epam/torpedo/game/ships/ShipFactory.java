package com.epam.torpedo.game.ships;

import java.util.Random;

import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.ships.types.FreeShip;
import com.epam.torpedo.game.ships.types.LShip;
import com.epam.torpedo.game.ships.types.LineShip;
import com.epam.torpedo.game.ships.types.TShip;

public class ShipFactory {

	private static final int MAX_LINESHIP_WIDTH = 4;
	private static final int NUMBER_OF_SHIPTYPES = 3;

	static public Ship getRandomShip(Dimension dimensionOfBattleField) {
		Ship ship;
		Random random = new Random();
		Coordinate coordinate = Coordinate.getRandomPosition(
				dimensionOfBattleField.getWidth(),
				dimensionOfBattleField.getHeight());

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

	static public Ship getFreeShip(Shape shape, Dimension dimensionOfBattleField) {
		Coordinate coordinate = Coordinate.getRandomPosition(
				dimensionOfBattleField.getWidth(),
				dimensionOfBattleField.getHeight());
		Ship ship = new FreeShip(shape);
		ship.setPosition(coordinate);
		return ship;
	}

}
