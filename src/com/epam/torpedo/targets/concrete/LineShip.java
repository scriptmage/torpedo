package com.epam.torpedo.targets.concrete;

import com.epam.torpedo.Ship;

public class LineShip extends Ship {

	private int length;

	public void setLength(int length) {
		if(length < 1) {
			throw new IllegalArgumentException("Ship's length must be more than 0");
		}
		this.length = length;
	}

	public LineShip() {
		length = 1;
	}

	public LineShip(int length) {
		setLength(length);
	}

	@Override
	public void createShape() {
		for (int i = 0; i < length; i++) {
			addShapePoint(getPositionX() + i, getPositionY());
		}
	}

}
