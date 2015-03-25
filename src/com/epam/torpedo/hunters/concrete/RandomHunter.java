package com.epam.torpedo.hunters.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;

public class RandomHunter extends Hunter {

	@Override
	public Coordinate nextShot() {
		Coordinate coordinate;

		do {
			int width = dimension.getWidth();
			int height = dimension.getHeight();
			coordinate = Coordinate.getRandomPosition(width, height);
		} while (isExists(coordinate));

		addShot(coordinate);
		return coordinate;
	}

}
