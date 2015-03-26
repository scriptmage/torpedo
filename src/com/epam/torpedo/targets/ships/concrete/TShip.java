package com.epam.torpedo.targets.ships.concrete;

import com.epam.torpedo.Ship;

public class TShip extends Ship {

	@Override
	public void createShape() {
		for (int i = 0; i < 3; i++) {
			addShapePoint(getPositionX() + i, getPositionY());
		}
		addShapePoint(getPositionX() + 1, getPositionY() - 1);
	}

}
