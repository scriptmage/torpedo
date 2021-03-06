package com.epam.torpedo.components;

import java.util.Random;

public class Coordinate {

	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	static public Coordinate getRandomCoordinate(int maxXPosition, int maxYPosition) {
		Random random = new Random();
		int positionX = random.nextInt(maxXPosition);
		int positionY = random.nextInt(maxYPosition);
		return new Coordinate(positionX, positionY);
	}
	
	static public Coordinate getRandomIntervalCoordinate(int minXPosition, int maxXPosition, int minYPosition, int maxYPosition) {
		Random random = new Random();
		int positionX = random.nextInt(maxXPosition - minXPosition) + minXPosition;
		int positionY = random.nextInt(maxYPosition - minYPosition) + minYPosition;
		return new Coordinate(positionX, positionY);
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

}
