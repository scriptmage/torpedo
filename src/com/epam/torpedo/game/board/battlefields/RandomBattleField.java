package com.epam.torpedo.game.board.battlefields;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Ship;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.ships.ShipFactory;

public class RandomBattleField extends BattleField {

	public RandomBattleField(Dimension dimension) {
		super(dimension);
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
		} while (size() < getNumberOfShips()
				&& iterateCounter < BattleField.ITERATION_TOLERANCE);

		checkTolerance(iterateCounter);
	}

}
