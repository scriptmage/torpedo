package com.epam.torpedo.components;

import com.epam.torpedo.game.GameConfig;

public class DimensionSplitter {

	private int counterOfBlocks;
	private int numberOfBlocks;
	private int divider;
	private Dimension dimension;

	public DimensionSplitter(int numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
		dimension = GameConfig.getDimension();
		divider = (int) Math.sqrt(numberOfBlocks);
	}

	public void next() {
		counterOfBlocks++;
		if (counterOfBlocks > numberOfBlocks - 1) {
			counterOfBlocks = 0;
		}
	}

	public Coordinate getPosition() {
		return Coordinate.getRandomIntervalCoordinate(getLeftSideOfBlock(), getRightSideOfBlock(), getTopSideOfBlock(), getBottomSideOfBlock());
	}

	private int getLeftSideOfBlock() {
		int widthOfBlock = dimension.getWidth() / divider;
		return widthOfBlock * (counterOfBlocks % divider);
	}

	private int getRightSideOfBlock() {
		int widthOfBlock = dimension.getWidth() / divider;
		return getLeftSideOfBlock() + widthOfBlock;
	}

	private int getTopSideOfBlock() {
		int heightOfBlock = dimension.getHeight() / divider;
		return heightOfBlock * (counterOfBlocks / divider);
	}

	private int getBottomSideOfBlock() {
		int heightOfBlock = dimension.getHeight() / divider;
		return getTopSideOfBlock() + heightOfBlock;
	}

}
