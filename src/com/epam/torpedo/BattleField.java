package com.epam.torpedo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.board.BattleFieldDrawer;

public abstract class BattleField {
	private Dimension dimension;
	private Set<Ship> battleField;
	private int numberOfShips = 10;

	protected static final int ITERATION_TOLERANCE = 25;

	public int getNumberOfShips() {
		return numberOfShips;
	}

	public void setNumberOfShips(int numberOfShips) {
		this.numberOfShips = numberOfShips;
	}

	public BattleField(Dimension dimension) {
		this.dimension = dimension;
		battleField = new HashSet<>();
	}

	public int getWidth() {
		return dimension.getWidth();
	}

	public int getHeight() {
		return dimension.getHeight();
	}

	public Dimension getDimension() {
		return dimension;
	}

	public boolean addShip(Ship ship) {
		ship.createShape();
		validatePosition(ship);
		return battleField.add(ship);
	}

	public void validatePosition(int x, int y) {
		if (!isValidPositionX(x)) {
			throw new IllegalArgumentException(
					"Illegal X position, must be between 0 and "
							+ (dimension.getWidth() - 1));
		}
		if (!isValidPositionY(y)) {
			throw new IllegalArgumentException(
					"Illegal Y position, must be between 0 and "
							+ (dimension.getHeight() - 1));
		}
	}

	private boolean isValidPositionX(int x) {
		return x >= 0 && x < dimension.getWidth();
	}

	private boolean isValidPositionY(int y) {
		return y >= 0 && y < dimension.getHeight();
	}

	public void validatePosition(Ship ship) {
		if (!isValidShipPosition(ship)) {
			throw new IllegalArgumentException("Invalid ship position");
		}

		if (!isEmptyArea(ship)) {
			throw new IllegalStateException(
					String.format(
							"This area [ %2dx%-2d ] already has a ship or too close to another one",
							ship.getPositionX(), ship.getPositionY()));
		}
	}

	private boolean isValidShipPosition(Ship ship) {
		return isValidShipPositionX(ship) && isValidShipPositionY(ship);
	}

	private boolean isValidShipPositionX(Ship ship) {
		return ship.getLeft() >= 0 && ship.getRight() < dimension.getWidth();
	}

	private boolean isValidShipPositionY(Ship ship) {
		return ship.getTop() >= 0 && ship.getBottom() < dimension.getHeight();
	}

	private boolean isEmptyArea(Ship ship) {
		CoordinateSet commonArea = getReservedArea();
		commonArea.retainAll(ship.getShape());
		return commonArea.isEmpty();
	}

	private CoordinateSet getReservedArea() {
		return calcReservedArea();
	}

	public CoordinateSet getAllShipCoords() {
		CoordinateSet pointsOfShips = new CoordinateSet();
		for (Ship ship : battleField) {
			CoordinateSet shape = ship.getShape();
			pointsOfShips.addAll(shape);
		}
		return pointsOfShips;
	}

	private CoordinateSet calcReservedArea() {
		CoordinateSet reservedArea = new CoordinateSet();
		Iterator<Coordinate> pointIterator = getAllShipCoords().iterator();
		while (pointIterator.hasNext()) {
			Coordinate coordinate = pointIterator.next();
			reservedArea.add(coordinate.getX() - 1, coordinate.getY());
			reservedArea.add(coordinate.getX(), coordinate.getY() - 1);
			reservedArea.add(coordinate.getX() + 1, coordinate.getY());
			reservedArea.add(coordinate.getX(), coordinate.getY() + 1);
		}
		return reservedArea;
	}

	public boolean isAliveShips() {
		boolean hasAlive = false;
		Iterator<Ship> iterator = battleField.iterator();
		while (!hasAlive && iterator.hasNext()) {
			Ship ship = iterator.next();
			hasAlive = ship.isAlive();
		}
		return hasAlive;
	}
	
	public boolean shoot(Hunter hunter) {
		Coordinate shoot = hunter.nextShot(dimension.getWidth(),
				dimension.getHeight());
		validatePosition(shoot.getX(), shoot.getY());

		boolean hasHit = false;

		Iterator<Ship> shipIterator = battleField.iterator();
		while (!hasHit && shipIterator.hasNext()) {
			Ship ship = shipIterator.next();
			if (ship.isHit(shoot.getX(), shoot.getY())) {
				hasHit = true;
				ship.decHealPoint();
				BattleFieldDrawer bfDrawer = new BattleFieldDrawer(this);
				bfDrawer.draw(hunter);
			}
		}

		return hasHit;
	}

	public int size() {
		return battleField.size();
	}

	protected void checkTolerance(int iterateCounter) {
		if (iterateCounter == ITERATION_TOLERANCE) {
			throw new IllegalStateException("Sorry, maybe the board ["
					+ dimension.getWidth() + "x" + dimension.getHeight()
					+ "] is very small for " + numberOfShips + " ships");
		}
	}

	public abstract void createBattleField();

}
