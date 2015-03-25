package com.epam.torpedo.components;

import com.epam.torpedo.resolvers.Resolver;
import com.epam.torpedo.resolvers.datasources.PropertyFileReader;

public class Config {

	public static final String CONFIG_FILE = "config.properties";
	private static PropertyFileReader propertyFileReader;

	public static Resolver getResolver() {
		if (propertyFileReader == null) {
			propertyFileReader = new PropertyFileReader(CONFIG_FILE);
		}
		return propertyFileReader;
	}

	public static Dimension getDimension() {
		int width = Integer.parseInt(propertyFileReader.get("boardWidth"));
		int height = Integer.parseInt(propertyFileReader.get("boardHeight"));
		return new Dimension(width, height);
	}

}
