package com.epam.torpedo.hunters;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.hunters.concrete.ConcretePositionHunter;
import com.epam.torpedo.hunters.concrete.PreciseHunter;
import com.epam.torpedo.hunters.concrete.RandomHunter;
import com.epam.torpedo.resolvers.Resolver;

public class HunterFactory {

	private static Hunter hunter;
	private static ConcretePositionHunter shooter;

	public static Hunter createHunter() {
		Resolver resolver = Config.getResolver();
		String hunterShotStrategyName = resolver.get("hunter");

		if (hunter == null) {
			switch (hunterShotStrategyName) {
			case "random":
				hunter = new RandomHunter();
				break;
			case "precise":
				hunter = new PreciseHunter();
				break;
			default:
				throw new IllegalArgumentException("Unknown hunter type: " + hunterShotStrategyName + "! Use the following: random, precise");
			}
			hunter.setDimension(Config.getDimension());
		}
		
		return hunter;
	}

	public static ConcretePositionHunter createShooter() {
		if (shooter == null) {
			shooter = new ConcretePositionHunter();
			shooter.setDimension(Config.getDimension());
		}
		return shooter;
	}

}
