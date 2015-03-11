package com.epam.torpedo.game.hunters;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;

public class ConcretePositionHunter extends Hunter {

	private Coordinate position;

	public void setPosition(int x, int y) {
		setPosition(new Coordinate(x, y));
	}

	public void setPosition(Coordinate coordinate) {
		position = coordinate;
	}
	
	@Override
	public Coordinate nextShot() {
		addShot(position);
		return position;
	}

}
