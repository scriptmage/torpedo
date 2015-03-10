package com.epam.torpedo;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;

public abstract class Hunter implements Shooter {
	protected Dimension dimension;

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public void setDimension(int widthOfBattleField, int heightOfBattleField) {
		this.dimension = new Dimension(widthOfBattleField, heightOfBattleField);
	}

	private CoordinateSet shots = new CoordinateSet();

	public void addShot(int x, int y) {
		shots.add(x, y);
	}

	public void addShot(Coordinate coordinate) {
		shots.add(coordinate);
	}

	public boolean isExists(int x, int y) {
		return shots.contains(x, y);
	}

	public boolean isExists(Coordinate coordinate) {
		return shots.contains(coordinate);
	}

	public CoordinateSet getShots() {
		return shots;
	}

	abstract public Coordinate nextShot();
}
