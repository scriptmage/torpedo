package com.epam.torpedo.options.datasources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.epam.torpedo.options.Options;

public class PropertiesOptions implements Options {

	private static final String CONFIG_FILE = "config.properties";
	private Properties properties;

	public PropertiesOptions() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(CONFIG_FILE));
		} catch (IOException e) {
			System.out.println("Properties file not found: " + CONFIG_FILE);
		}
	}

	@Override
	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}

}
