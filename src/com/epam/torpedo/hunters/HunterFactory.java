package com.epam.torpedo.hunters;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.board.BattleFieldFactory;
import com.epam.torpedo.hunters.concrete.ConcretePositionHunter;
import com.epam.torpedo.hunters.concrete.PreciseHunter;
import com.epam.torpedo.hunters.concrete.RandomHunter;

public class HunterFactory {

	private static Hunter hunter;
	private static Hunter shooter;

	public static Hunter createHunter(String hunterType) {
		if (hunter == null) {
			switch (hunterType) {
			case "random":
				hunter = new RandomHunter();
				break;
			case "precise":
				hunter = new PreciseHunter();
				break;
			default:
				throw new IllegalArgumentException("Unknown hunter: " + hunterType + "! Use the following: random, precise");
			}

			BattleField battleField = BattleFieldFactory.createBattleField();
			hunter.setDimension(battleField.getDimension());
		}
		return hunter;
	}

	public static Hunter createShooter() {
		if (shooter == null) {
			shooter = new ConcretePositionHunter();
			BattleField battleField = BattleFieldFactory.createBattleField();
			shooter.setDimension(battleField.getDimension());
		}
		return shooter;
	}

}
