package com.epam.torpedo.targets.ships;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.exceptions.InvalidShipPositionException;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.targets.ships.concrete.NullShip;

public class ShipManager {
	// TODO fordított tárolás, a hajó pontokat  tárolom hajóval HashMapben, nem a hajókat tárolom pontokkal --> megfordítom a viszonyt
	private Set<Ship> ships = new HashSet<>();
	
	public boolean add(Ship ship) {
		boolean result = true;
		try {
			ship.createShape();
			validatePosition(ship);
			ships.add(ship);
			System.out.println(String.format("New ship is here: %d %d", ship.getPositionX(), ship.getPositionY()));
		} catch(InvalidShipPositionException e) {
			result = false;
		}
		return result;
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
	
	public boolean validatePosition(Ship ship) {
		if (!isValidShipPosition(ship)) {
			throw new InvalidShipPositionException();
		}

		if (!isEmptyArea(ship)) {
			throw new IllegalStateException(String.format("This area [ %s ] already has a ship or too close to another one", ship.getPosition()));
		}
		return true;
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
		Iterator<Ship> iterator = ships.iterator();
		while (iterator.hasNext()) {
			Ship ship = iterator.next();
			coordinateSet.addAll(ship.getShape());
		}
		return coordinateSet;
	}

	public int getShipNumber() {
		return ships.size();
	}

	public Ship get(Coordinate coordinate) {
		Ship result = new NullShip();

		Iterator<Ship> iterator = ships.iterator();
		while (iterator.hasNext()) {
			Ship ship = iterator.next();
			CoordinateSet shape = ship.getShape();
			if (shape.contains(coordinate)) {
				result = ship;
			}
		}

		return result;
	}

	public Ship get(int x, int y) {
		return get(new Coordinate(x, y));
	}

	public boolean isAliveShips() {
		boolean hasAlive = false;
		Iterator<Ship> iterator = ships.iterator();
		while (!hasAlive && iterator.hasNext()) {
			Ship ship = iterator.next();
			hasAlive = ship.isAlive();
		}
		return hasAlive;
	}

}