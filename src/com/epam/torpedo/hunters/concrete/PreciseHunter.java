package com.epam.torpedo.hunters.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.GameConfig;

public class PreciseHunter extends Hunter {

	CoordinateSet targetPoints = new CoordinateSet();

	@Override
	public Coordinate nextShot() {
		Coordinate coordinate;
		
		do {
			if (targetPoints.isEmpty()) {
				int width = dimension.getWidth();
				int height = dimension.getHeight();
				coordinate = Coordinate.getRandomPosition(width, height);
			} else {
				coordinate = targetPoints.pop();
			}
			
		} while (isExists(coordinate));

		addShot(coordinate);
		return coordinate;
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		if(isValidCoordinate(coordinate)) {
			targetPoints.add(coordinate);
		}
	}

	private boolean isValidCoordinate(Coordinate coordinate) {
		return isValidXPosition(coordinate) && isValidYPosition(coordinate);
	}

	private boolean isValidXPosition(Coordinate coordinate) {
		Dimension dimensionOfBattlefield = GameConfig.getDimension();
		return coordinate.getX() >= 0 && coordinate.getX() < dimensionOfBattlefield.getWidth();
	}

	private boolean isValidYPosition(Coordinate coordinate) {
		Dimension dimensionOfBattlefield = GameConfig.getDimension();
		return coordinate.getY() >= 0 && coordinate.getY() < dimensionOfBattlefield.getHeight();
	}

	@Override
	public void clear() {
		addShots(targetPoints);
		targetPoints.clear();
	}

}
