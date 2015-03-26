package com.epam.torpedo.ships.types;

import com.epam.torpedo.Ship;

public class NullShip extends Ship {

	@Override
	public void createShape() {
		
	}

	@Override
	public boolean isAlive() {
		return false;
	}
	
}
