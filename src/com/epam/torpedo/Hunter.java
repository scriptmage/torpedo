package com.epam.torpedo;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;

public abstract class Hunter implements Shooter {
	protected Dimension dimension;
	private CoordinateSet shots = new CoordinateSet();
	private Coordinate lastShot;
	
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public void setDimension(int widthOfBattleField, int heightOfBattleField) {
		this.dimension = new Dimension(widthOfBattleField, heightOfBattleField);
	}

	public void addShot(int x, int y) {
		addShot(new Coordinate(x, y));
	}

	public void addShot(Coordinate coordinate) {
		lastShot = coordinate;
		shots.add(coordinate);
	}
	
	public void addShots(CoordinateSet coordinateSet) {
		shots.addAll(coordinateSet);
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
	
	public Coordinate getLastShot() {
		return lastShot;
	}
	
	abstract public Coordinate nextShot();
	abstract public void setPosition(Coordinate coordinate);
	abstract public void clear();
}
