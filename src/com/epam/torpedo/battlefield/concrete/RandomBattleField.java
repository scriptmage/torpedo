package com.epam.torpedo.battlefield.concrete;

import com.epam.torpedo.Ship;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.targets.ShipFactory;

public class RandomBattleField extends BattleField {

	@Override
	public void createBattleField() {
		int iterateCounter = 0;

		do {
			Ship ship = ShipFactory.getRandomShip();
			try {
				addShip(ship);
				iterateCounter = 0;
			} catch (IllegalArgumentException e) {
				System.out.println("Generate new coordinates");
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}

			iterateCounter++;
		} while (getNumberOfShips() < getMaxNumberOfShips() && iterateCounter < GameConfig.ITERATION_TOLERANCE);

		checkTolerance(iterateCounter);
	}

}
