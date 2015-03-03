package com.epam.torpedo.game.hunters;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;

public class RandomHunter extends Hunter {

	@Override
	public Coordinate nextShot(int widthBattleField, int heightBattleField) {
		Coordinate coordinate;

		do {
			coordinate = Coordinate.getRandomPosition(widthBattleField,
					heightBattleField);
		} while (isExists(coordinate));

		System.out.println(String.format("Shoot: [ %2d x %-2d ]",
				coordinate.getX(), coordinate.getY()));

		addShot(coordinate);
		return coordinate;
	}

}
