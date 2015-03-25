package com.epam.torpedo.resolvers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.epam.torpedo.components.Config;

public class PropertyLoader {
	protected Properties properties;

	public PropertyLoader() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(Config.CONFIG_FILE));
		} catch (IOException e) {
			System.out.println("Properties file not found: " + Config.CONFIG_FILE);
		}
	}

}
