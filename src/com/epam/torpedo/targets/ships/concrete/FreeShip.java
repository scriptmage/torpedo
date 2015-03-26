package com.epam.torpedo.targets.ships.concrete;

import java.util.Iterator;

import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.targets.Shape;

public class FreeShip extends Ship {

	private Shape shape;

	public FreeShip(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void createShape() {
		Iterator<Coordinate> pointIterator = shape.iterator();
		while (pointIterator.hasNext()) {
			Coordinate point = pointIterator.next();

			/*
			 *  Must decrement X and Y point, because shape origo is 0x0
			 *  and origo is 1x1 in file
			 */
			addShapePoint(getPositionX() + point.getX(), getPositionY() + point.getY());
		}
	}

}
