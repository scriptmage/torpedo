package com.epam.torpedo.battlefield.concrete;

import com.epam.torpedo.Ship;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.ships.ShipFactory;

public class RandomBattleField extends BattleField {

	public RandomBattleField(int numberOfShips) {
		setNumberOfShips(numberOfShips);
	}

	@Override
	public void createBattleField() {
		int iterateCounter = 0;

		do {
			Ship ship = ShipFactory.getRandomShip(getDimension());
			try {
				addShip(ship);
				iterateCounter = 0;
			} catch (IllegalArgumentException e) {
				System.out.println("Generate new coordinates");
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}

			iterateCounter++;
		} while (size() < getNumberOfShips() && iterateCounter < Config.ITERATION_TOLERANCE);

		checkTolerance(iterateCounter);
	}

}
