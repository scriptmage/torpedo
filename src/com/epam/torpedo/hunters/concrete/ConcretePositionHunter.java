package com.epam.torpedo.hunters.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;

public class ConcretePositionHunter extends Hunter {

	private Coordinate position;

	public void setPosition(Object[] params) {
		int x = Integer.parseInt((String) params[0]);
		int y = Integer.parseInt((String) params[1]);
		setPosition(x, y);
	}
	
	public void setPosition(int x, int y) {
		setPosition(new Coordinate(x, y));
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		position = coordinate;
	}
	
	public Coordinate getPosition() {
		return position;
	}
	
	@Override
	public Coordinate nextShot() {
		addShot(position);
		return position;
	}

	@Override
	public void clear() {
	}

}
