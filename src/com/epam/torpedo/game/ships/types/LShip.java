package com.epam.torpedo.game.ships.types;

import com.epam.torpedo.Ship;

public class LShip extends Ship {

	@Override
	public void createShape() {
		for (int i = 0; i < 3; i++) {
			addShapePoint(getPositionX() + i, getPositionY());
		}
		addShapePoint(getPositionX(), getPositionY() - 1);
	}

}
