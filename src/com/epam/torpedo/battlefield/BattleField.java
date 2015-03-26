package com.epam.torpedo.battlefield;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.epam.torpedo.Board;
import com.epam.torpedo.Drawable;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Ship;
import com.epam.torpedo.battlefield.drawers.BattleFieldDrawer;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.hunters.HunterFactory;
import com.epam.torpedo.targets.ships.ShipManager;

public abstract class BattleField extends Board {
	private ShipManager ships;
	private int maxNumberOfShips = 10;
	private Drawable drawer;

	public int getNumberOfShips() {
		return ships.getShipNumber();
	}

	public int getMaxNumberOfShips() {
		return maxNumberOfShips;
	}

	public void setMaxNumberOfShips(int maxNumberOfShips) {
		this.maxNumberOfShips = maxNumberOfShips;
	}

	public BattleField() {
		// TODO ezt a függést megszüntetni
		drawer = new BattleFieldDrawer();
		ships = new ShipManager();
	}

	public void addShip(Ship ship) {
		ships.add(ship);
	}

	public Ship getShip(Coordinate position) {
		return ships.get(position);
	}

	public boolean shoot(Hunter hunter) {
		boolean hasHit = false;
		Coordinate shoot = hunter.nextShot();
		validatePosition(shoot);

		Ship ship = ships.get(shoot);
		boolean hasHit = ship.isAlive();

		if (hasHit) {
			ship.decHealPoint();

			if (drawer != null) {
				Hunter shooter = HunterFactory.createShooter();
				shooter.addShot(shoot);
				drawer.draw(ships.getShipCoords(), shooter);
			}
		}

		return hasHit;
	}

	protected void checkTolerance(int iterateCounter) {
		if (iterateCounter == GameConfig.ITERATION_TOLERANCE) {
			throw new IllegalStateException("Sorry, maybe the board [" + getWidth() + "x" + getHeight() + "] is very small for " + maxNumberOfShips + " ships");
		}
		return resultShip;
	}

	public boolean isAliveShips() {
		return ships.isAliveShips();
	}

	public abstract void createBattleField();

}
