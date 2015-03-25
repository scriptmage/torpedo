package com.epam.torpedo;

import java.util.Iterator;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.ships.Shape;

public abstract class Ship {

	private Coordinate position;
	private Shape shape;
	private int healPoint;

	public Ship() {
		shape = new Shape();
	}

	public void setPosition(Coordinate coordinate) {
		position = coordinate;
	}

	public void setPosition(int x, int y) {
		position = new Coordinate(x, y);
	}

	public int getPositionX() {
		return position.getX();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		Ship other = (Ship) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	public int getPositionY() {
		return position.getY();
	}

	public boolean isAlive() {
		return healPoint > 0;
	}

	public boolean isHit(Coordinate coordinate) {
		return isHit(coordinate.getX(), coordinate.getY());
	}

	public boolean isHit(int x, int y) {
		return shape.contains(x, y);
	}

	public void addShapePoint(int x, int y) {
		if (shape.add(x, y)) {
			calcHealPoint();
			shape.calcArea();
		}
	}

	private void calcHealPoint() {
		healPoint = shape.size();
	}

	public Iterator<Coordinate> getShapeIterator() {
		return shape.iterator();
	}
	
	public CoordinateSet getShape() {
		return shape.getPoints();
	}

	public int getTop() {
		return shape.getTop();
	}

	public int getBottom() {
		return shape.getBottom();
	}

	public int getLeft() {
		return shape.getLeft();
	}

	public int getRight() {
		return shape.getRight();
	}
	
	public void decHealPoint() {
		healPoint--;
	}
	
	abstract public void createShape();

}
