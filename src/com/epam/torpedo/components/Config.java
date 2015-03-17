package com.epam.torpedo.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Game;
import com.epam.torpedo.Hunter;

public class Config {

	private static final String CONFIG_FILE = "config.properties";
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(CONFIG_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Dimension getBattleFieldDimension() {
		int width = Integer.parseInt(properties.getProperty("battlefield_width", "25"));
		int height = Integer.parseInt(properties.getProperty("battlefield_height", "25"));
		return new Dimension(width, height);
	}

	public static File getData() {
		return new File(properties.getProperty("data_file", "ships.dat"));
	}

	public static BattleField getBattleField() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String className = properties.getProperty("battlefield", "RandomBattleField");
		return (BattleField) Class.forName(className).newInstance();
	}

	public static Game getGame() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String className = properties.getProperty("game", "SocketGame");
		return (Game) Class.forName(className).newInstance();
	}

	public static Hunter getHunter() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String className = properties.getProperty("hunter", "RandomHunter");
		return (Hunter) Class.forName(className).newInstance();
	}

}
