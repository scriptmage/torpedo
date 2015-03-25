package com.epam.torpedo.resolvers.datasources;

import java.io.File;
import java.util.Properties;

import com.epam.torpedo.resolvers.PropertyFileLoader;
import com.epam.torpedo.resolvers.Resolver;

public class PropertyFileReader implements Resolver {

	private Properties properties;
	private PropertyFileLoader loader;
	
	public PropertyFileReader(String configFile) {
		loader = new PropertyFileLoader(new File(configFile));
		properties = loader.getProperties();
	}

	@Override
	public String get(String name) {
		return properties.getProperty(name).toLowerCase();
	}

}