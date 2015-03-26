package com.epam.torpedo.game;

import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.resolvers.Resolver;
import com.epam.torpedo.resolvers.datasources.PropertyFileReader;

public class GameConfig {

	public static final String CONFIG_FILE = "config.properties";
	public static final int ITERATION_TOLERANCE = 25;
	
	private static PropertyFileReader propertyFileReader = new PropertyFileReader(CONFIG_FILE);

	public static Resolver getResolver() {
		return propertyFileReader;
	}

	public static Dimension getDimension() {
		int width = Integer.parseInt(propertyFileReader.get("boardWidth"));
		int height = Integer.parseInt(propertyFileReader.get("boardHeight"));
		return new Dimension(width, height);
	}

}
