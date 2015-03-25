package com.epam.torpedo.resolvers.datasources;

import com.epam.torpedo.resolvers.GameModeResolver;
import com.epam.torpedo.resolvers.PropertyLoader;

public class PropertyReader extends PropertyLoader implements GameModeResolver {

	private String propertyName;
	
	public PropertyReader(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String getGameMode() {
		return properties.getProperty(propertyName).toLowerCase();
	}

}