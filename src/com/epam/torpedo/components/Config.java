package com.epam.torpedo.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.epam.torpedo.Drawable;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.board.drawers.BattleFieldDrawer;
import com.epam.torpedo.hunters.RandomHunter;

public class Config {

	private static final String CONFIG_FILE = "config.properties";

	private static Properties properties;

//	private static BattleField battleField;
	private static Hunter hunter;
	private static Drawable battleFieldDrawer;

	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(CONFIG_FILE));
		} catch (IOException e) {
			System.out.println("Properties file not found: " + CONFIG_FILE);
		}
	}

	public static Dimension getBattleFieldDimension() {
		int width = Integer.parseInt(properties.getProperty("boardWidth", "25"));
		int height = Integer.parseInt(properties.getProperty("boardHeight", "25"));
		return new Dimension(width, height);
	}

	public static File getDataFile() {
		return new File(properties.getProperty("dataFile", "ships.dat"));
	}

//	public static BattleField getBattleField() {
//		try {
//			if(battleField == null) {
//				String className = properties.getProperty("battleField", "com.epam.torpedo.game.board.battlefields.FileBattleField");
//				battleField = (BattleField) Class.forName(className).newInstance();
//			}
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			battleField = new FileBattleField();
//		}
//		return battleField;
//	}

	public static Hunter getHunter() {
		try {
			if(hunter == null) {
				String className = properties.getProperty("hunter", "com.epam.torpedo.game.hunters.RandomHunter");
				hunter = (Hunter) Class.forName(className).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			hunter = new RandomHunter();
		}
		return hunter;
	}

	public static Drawable getBattleFieldDrawer() {
		try {
			if(battleFieldDrawer == null) {
				String className = properties.getProperty("battleFieldDrawer", "com.epam.torpedo.game.board.BattleFieldDrawer");
				battleFieldDrawer = (Drawable) Class.forName(className).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			battleFieldDrawer = new BattleFieldDrawer();
		}
		return battleFieldDrawer;
	}
	
}
