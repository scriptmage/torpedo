package com.epam.torpedo.game.hunters;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;

public class RandomHunter extends Hunter {

	@Override
	public Coordinate nextShot() {
		Coordinate coordinate;

		do {
			coordinate = Coordinate.getRandomPosition(dimension.getWidth(),
					dimension.getHeight());
		} while (isExists(coordinate));

		addShot(coordinate);
		return coordinate;
	}

}
