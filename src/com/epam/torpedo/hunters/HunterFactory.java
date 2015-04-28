package com.epam.torpedo.hunters;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.hunters.concrete.ConcretePositionHunter;
import com.epam.torpedo.hunters.concrete.PreciseHunter;
import com.epam.torpedo.hunters.concrete.RandomHunter;
import com.epam.torpedo.resolvers.Resolver;

public class HunterFactory {

	private static Hunter hunter;
	private static ConcretePositionHunter shooter;
	
	static {
		shooter = new ConcretePositionHunter();
		shooter.setDimension(GameConfig.getDimension());
	}

	public static Hunter getHunter() {
		if (hunter == null) {
			Resolver resolver = GameConfig.getResolver();
			String hunterShotStrategyName = resolver.get("hunter");

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
			hunter.setDimension(GameConfig.getDimension());
		}
		
		return hunter;
	}

	public static ConcretePositionHunter createShooter() {
		return shooter;
	}

}
