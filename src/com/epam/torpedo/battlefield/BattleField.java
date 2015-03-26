package com.epam.torpedo.battlefield;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.epam.torpedo.Board;
import com.epam.torpedo.Drawable;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Ship;
import com.epam.torpedo.battlefield.drawers.BattleFieldDrawer;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.hunters.HunterFactory;
import com.epam.torpedo.ships.concrete.NullShip;

public abstract class BattleField extends Board {
	private Set<Ship> battleField;
	private int numberOfShips = 10;
	private Drawable drawer;

	public int getNumberOfShips() {
		return numberOfShips;
	}

	public void setNumberOfShips(int numberOfShips) {
		this.numberOfShips = numberOfShips;
	}

	public BattleField() {
		// TODO ezt a függést megszüntetni
		drawer = new BattleFieldDrawer();
		battleField = new HashSet<>();
	}

	public boolean addShip(Ship ship) {
		ship.createShape();
		validatePosition(ship);
		return battleField.add(ship);
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

	private boolean isValidShipPosition(Ship ship) {
		return isValidShipPositionX(ship) && isValidShipPositionY(ship);
	}

	private boolean isValidShipPositionX(Ship ship) {
		return ship.getLeft() >= 0 && ship.getRight() < getWidth();
	}

	private boolean isValidShipPositionY(Ship ship) {
		return ship.getTop() >= 0 && ship.getBottom() < getHeight();
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
		boolean hasHit = false;
		Coordinate shoot = hunter.nextShot();
		validatePosition(shoot);

		Iterator<Ship> shipIterator = battleField.iterator();
		while (!hasHit && shipIterator.hasNext()) {
			Ship ship = shipIterator.next();
			if (ship.isHit(shoot)) {
				hasHit = true;
				ship.decHealPoint();

				if (drawer != null) {
					Hunter shooter = HunterFactory.createShooter();
					shooter.addShot(shoot);
					drawer.draw(getAllShipCoords(), shooter);
				}
			}
		}

		return hasHit;
	}

	public Ship getShip(Coordinate coordinate) {
		return getShip(coordinate.getX(), coordinate.getY());
	}

	public Ship getShip(int x, int y) {
		Ship resultShip = new NullShip();
		Iterator<Ship> shipIterator = battleField.iterator();
		while (shipIterator.hasNext()) {
			Ship ship = shipIterator.next();
			if (ship.isHit(x, y)) {
				resultShip = ship;
			}
		}
		return resultShip;
	}

	public int size() {
		return battleField.size();
	}

	protected void checkTolerance(int iterateCounter) {
		if (iterateCounter == GameConfig.ITERATION_TOLERANCE) {
			throw new IllegalStateException("Sorry, maybe the board [" + getWidth() + "x" + getHeight() + "] is very small for " + numberOfShips + " ships");
		}
	}

	public abstract void createBattleField();

}
