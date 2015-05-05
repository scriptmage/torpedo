package com.epam.torpedo.battlefield;

import java.util.Arrays;
import java.util.List;

import com.epam.torpedo.Board;
import com.epam.torpedo.Drawable;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.hunters.HunterFactory;
import com.epam.torpedo.targets.Shape;
import com.epam.torpedo.targets.ships.ShipManager;

public abstract class BattleField extends Board {
	private ShipManager ships;
	private int maxNumberOfShips = 10;
	
	public BattleField() {
		ships = new ShipManager();
	}

	public int getNumberOfShips() {
		return ships.getShipNumber();
	}

	public int getMaxNumberOfShips() {
		return maxNumberOfShips;
	}

	public void setMaxNumberOfShips(int maxNumberOfShips) {
		this.maxNumberOfShips = maxNumberOfShips;
	}

	public void addShip(Ship ship) {
		ships.add(ship);
	}

	public Ship getShip(Coordinate position) {
		return ships.get(position);
	}

	public boolean shoot(Hunter hunter) {
		Coordinate shoot = hunter.nextShot();
		validatePosition(shoot);

		Ship ship = ships.get(shoot);
		boolean hasHit = ship.isAlive();

		if (hasHit) {
			ship.decHealPoint();
			draw(shoot);
		}

		return hasHit;
	}

	private void draw(Coordinate shoot) {
		Drawable drawer = BattleFieldDrawerFactory.getDrawer();
		if (drawer != null) {
			Hunter shooter = HunterFactory.getShooter();
			shooter.addShot(shoot);
			drawer.draw(ships.getShipCoords(), shooter);
		}
	}

	protected void checkTolerance(int iterateCounter) {
		if (iterateCounter == GameConfig.ITERATION_TOLERANCE) {
			throw new IllegalStateException("Sorry, maybe the board [" + getWidth() + "x" + getHeight() + "] is very small for " + maxNumberOfShips + " ships");
		}
	}

	public boolean isAliveShips() {
		return ships.isAliveShips();
	}
	
	protected void shortByShipSize(List<Shape> ships) {
		Shape[] unsortedShips = new Shape[ships.size()];
		for (int i = 0; i < ships.size(); i++) {
			unsortedShips[i] = ships.get(i);
		}
		Arrays.sort(unsortedShips);
		ships.clear();
		for(Shape shapeOfShip: unsortedShips) {
			ships.add(shapeOfShip);
		}
	}

	public abstract void createBattleField();

}
