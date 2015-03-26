package com.epam.torpedo.ships;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.ships.concrete.NullShip;

public class ShipManager {
	private Map<Coordinate, Ship> battleField = new HashMap<>();

	public void add(Ship ship) {
		ship.createShape();
		validatePosition(ship);
		int positionX = ship.getPositionX();
		int positionY = ship.getPositionY();
		battleField.put(new Coordinate(positionX, positionY), ship);
	}

	private boolean isValidShipPosition(Ship ship) {
		return isValidShipPositionX(ship) && isValidShipPositionY(ship);
	}

	private boolean isValidShipPositionX(Ship ship) {
		Dimension dimension = GameConfig.getDimension();
		return ship.getLeft() >= 0 && ship.getRight() < dimension.getWidth();
	}

	private boolean isValidShipPositionY(Ship ship) {
		Dimension dimension = GameConfig.getDimension();
		return ship.getTop() >= 0 && ship.getBottom() < dimension.getHeight();
	}

	public void validatePosition(Ship ship) {
		if (!isValidShipPosition(ship)) {
			throw new IllegalArgumentException("Invalid ship position");
		}

		if (!isEmptyArea(ship)) {
			throw new IllegalStateException(String.format("This area [ %2dx%-2d ] already has a ship or too close to another one", ship.getPositionX(),
					ship.getPositionY()));
		}
	}

	private boolean isEmptyArea(Ship ship) {
		CoordinateSet commonArea = getReservedArea();
		commonArea.retainAll(ship.getShape());
		return commonArea.isEmpty();
	}

	private CoordinateSet getReservedArea() {
		return calcReservedArea();
	}

	private CoordinateSet calcReservedArea() {
		CoordinateSet reservedArea = new CoordinateSet();
		Iterator<Coordinate> pointIterator = getShipCoords().iterator();
		while (pointIterator.hasNext()) {
			Coordinate coordinate = pointIterator.next();
			reservedArea.add(coordinate.getX() - 1, coordinate.getY());
			reservedArea.add(coordinate.getX(), coordinate.getY() - 1);
			reservedArea.add(coordinate.getX() + 1, coordinate.getY());
			reservedArea.add(coordinate.getX(), coordinate.getY() + 1);
		}
		return reservedArea;
	}

	public CoordinateSet getShipCoords() {
		CoordinateSet coordinateSet = new CoordinateSet();
		Set<Entry<Coordinate, Ship>> ships = battleField.entrySet();
		Iterator<Entry<Coordinate, Ship>> iterator = ships.iterator();
		while (iterator.hasNext()) {
			Entry<Coordinate, Ship> ship = iterator.next();
			coordinateSet.add(ship.getKey());
		}
		return coordinateSet;
	}

	public int getShipNumber() {
		return battleField.size();
	}

	public Ship get(Coordinate coordinate) {
		Ship result = new NullShip();
		if (battleField.containsKey(coordinate)) {
			result = battleField.get(coordinate);
		}
		return result;
	}

	public Ship get(int x, int y) {
		return get(new Coordinate(x, y));
	}

	public boolean isAliveShips() {
		boolean hasAlive = false;
		Set<Entry<Coordinate, Ship>> ships = battleField.entrySet();
		Iterator<Entry<Coordinate, Ship>> iterator = ships.iterator();
		while (!hasAlive && iterator.hasNext()) {
			Entry<Coordinate, Ship> entry = iterator.next();
			Ship ship = entry.getValue();
			hasAlive = ship.isAlive();
		}
		return hasAlive;
	}

}