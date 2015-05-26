package com.epam.torpedo.resolvers.loaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileLoader {
  private Properties properties;

  public PropertyFileLoader(File configFile) {
    properties = new Properties();
    try {
      properties.load(new FileInputStream(configFile));
    } catch (IOException e) {
      System.out.println("Properties file not found: " + configFile.getName());
    }
  }

  public Properties getProperties() {
    return properties;
  }

}
