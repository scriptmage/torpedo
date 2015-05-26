package com.epam.torpedo.resolvers.datasources;

import com.epam.torpedo.resolvers.Resolver;
import com.epam.torpedo.resolvers.loaders.PropertyFileLoader;

import java.io.File;
import java.util.Properties;

public class PropertyFileReader implements Resolver {

  private Properties         properties;
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