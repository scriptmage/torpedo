package com.epam.torpedo;

import com.epam.torpedo.components.Config;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.Dimension;

public class Board {
	private Dimension dimension;

	public Board() {
		dimension = Config.getDimension();
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public void setDimension(int widthOfBattleField, int heightOfBattleField) {
		this.dimension = new Dimension(widthOfBattleField, heightOfBattleField);
	}

	public int getWidth() {
		return dimension.getWidth();
	}

	public int getHeight() {
		return dimension.getHeight();
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void validatePosition(Coordinate coordinate) {
		validatePosition(coordinate.getX(), coordinate.getY());
	}

	public void validatePosition(int x, int y) {
		if (!isValidPositionX(x)) {
			throw new IllegalArgumentException("Illegal X position, must be between 0 and " + (dimension.getWidth() - 1));
		}
		if (!isValidPositionY(y)) {
			throw new IllegalArgumentException("Illegal Y position, must be between 0 and " + (dimension.getHeight() - 1));
		}
	}

	private boolean isValidPositionX(int x) {
		return x >= 0 && x < dimension.getWidth();
	}

	private boolean isValidPositionY(int y) {
		return y >= 0 && y < dimension.getHeight();
	}

}
