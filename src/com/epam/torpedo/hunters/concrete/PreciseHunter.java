package com.epam.torpedo.hunters.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.battlefield.BattleFieldFactory;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.GameConfig;

public class PreciseHunter extends Hunter {

	CoordinateSet targetPoints = new CoordinateSet();
	int countEvenPosition;
	int numbersOfPossibleEvenPositions;
	int quarter = 1;

	public PreciseHunter() {
		Dimension dimensionOfbattlefield = BattleFieldFactory.getBattleField().getDimension();
		numbersOfPossibleEvenPositions = (int) (dimensionOfbattlefield.getWidth() * dimensionOfbattlefield.getHeight() / 2);
		setDimension(dimensionOfbattlefield);
	}

	@Override
	public Coordinate nextShot() {
		Coordinate coordinate;

		do {
			coordinate = getRandomGridPosition();
			if (!targetPoints.isEmpty()) {
				coordinate = targetPoints.pop();
			}
		} while (isExists(coordinate));

		addShot(coordinate);
		return coordinate;
	}

	public Coordinate getRandomGridPosition() {
		Coordinate position;

		switch (quarter) {
		case 1:
			position = Coordinate.getRandomIntervalCoordinate(0, dimension.getWidth() / 2, 0, dimension.getHeight() / 2);
			break;
		case 2:
			position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2, dimension.getWidth(), 0, dimension.getHeight() / 2);
			break;
		case 3:
			position = Coordinate.getRandomIntervalCoordinate(0, dimension.getWidth() / 2, dimension.getHeight() / 2, dimension.getHeight());
			break;
		default:
			position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2, dimension.getWidth(), dimension.getHeight() / 2, dimension.getHeight());
		}
		
		quarter++;
		if (quarter > 4) {
			quarter = 1;
		}

		int posX = position.getX();

		if (countEvenPosition <= numbersOfPossibleEvenPositions) {
			posX = 1;
			if (isEven(position.getY())) {
				posX = position.getX() & -2;
				countEvenPosition++;
			} else if (isEven(position.getX()) && position.getX() > 3) {
				// default value of posX is 1, so it would be unnecessary tested
				// position.getX() > 1, because 2 - 1 is 1
				posX = position.getX() - 1;
			}
		}

		return new Coordinate(posX, position.getY());
	}

	private boolean isEven(int number) {
		return number % 2 == 0;
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		if (isValidCoordinate(coordinate)) {
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
