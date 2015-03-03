package com.epam.torpedo.components;

public class Dimension {
	private int width;
	private int height;

	public Dimension(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public void setWidth(int width) {
		if(!isValidWidth(width)) {
			throw new IllegalArgumentException("The width must be between 1 and " + Integer.MAX_VALUE);
		}
		this.width = width;
	}

	private boolean isValidWidth(int width) {
		return width > 0 && width <= Integer.MAX_VALUE;
	}

	public void setHeight(int height) {
		if(!isValidHeight(height)) {
			throw new IllegalArgumentException("The height must be between 1 and " + Integer.MAX_VALUE);
		}
		this.height = height;
	}

	private boolean isValidHeight(int height) {
		return isValidWidth(height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
