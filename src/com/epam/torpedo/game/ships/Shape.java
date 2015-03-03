package com.epam.torpedo.game.ships;

import java.util.Iterator;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;

public class Shape {
	private CoordinateSet points;
	private Coordinate topLeft;
	private Coordinate bottomRight;

	public Shape() {
		points = new CoordinateSet();
	}

	public CoordinateSet getPoints() {
		return points;
	}
	
	public Iterator<Coordinate> iterator() {
		return points.iterator();
	}

	public boolean contains(int x, int y) {
		return points.contains(x, y);
	}

	public int size() {
		return points.size();
	}

	public boolean add(int x, int y) {
		return points.add(x, y);
	}

	public int getTop() {
		return topLeft.getY();
	}

	public int getBottom() {
		return bottomRight.getY();
	}

	public int getLeft() {
		return topLeft.getX();
	}

	public int getRight() {
		return bottomRight.getX();
	}

	public void calcArea() {
		topLeft = calcTopLeftCoordinate();
		bottomRight = calcBottomRightCoordinate();
	}

	private Coordinate calcTopLeftCoordinate() {
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;

		Iterator<Coordinate> iterator = points.iterator();
		while (iterator.hasNext()) {
			Coordinate coordinate = iterator.next();
			if (x > coordinate.getX()) {
				x = coordinate.getX();
			}
			if (y > coordinate.getY()) {
				y = coordinate.getY();
			}
		}

		return new Coordinate(x, y);
	}

	private Coordinate calcBottomRightCoordinate() {
		int x = 0;
		int y = 0;

		Iterator<Coordinate> iterator = points.iterator();
		while (iterator.hasNext()) {
			Coordinate coordinate = iterator.next();
			if (x < coordinate.getX()) {
				x = coordinate.getX();
			}
			if (y < coordinate.getY()) {
				y = coordinate.getY();
			}
		}
		return new Coordinate(x, y);
	}

}
