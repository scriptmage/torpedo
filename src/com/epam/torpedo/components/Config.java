package com.epam.torpedo.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.Startable;
import com.epam.torpedo.game.board.BattleFieldDrawer;
import com.epam.torpedo.game.board.battlefields.FileBattleField;
import com.epam.torpedo.game.hunters.RandomHunter;
import com.epam.torpedo.game.types.SocketGame;

public class Config {

	private static final String CONFIG_FILE = "config.properties";

	private static Properties properties;

	private static BattleField battleField;
	private static Startable game;
	private static Hunter hunter;
	private static BattleFieldDrawer battleFieldDrawer;

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

	public static BattleField getBattleField() {
		try {
			if(battleField == null) {
				String className = properties.getProperty("battleField", "com.epam.torpedo.game.board.battlefields.FileBattleField");
				battleField = (BattleField) Class.forName(className).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			battleField = new FileBattleField();
		}
		return battleField;
	}

	public static Startable getGame() {
		try {
			if(game == null) {
				String className = properties.getProperty("game", "com.epam.torpedo.game.types.SocketGame");
				game = (Startable) Class.forName(className).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			game = new SocketGame();
		}
		return game;
	}

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

	public static BattleFieldDrawer getBattleFieldDrawer() {
		try {
			if(battleFieldDrawer == null) {
				String className = properties.getProperty("battleFieldDrawer", "com.epam.torpedo.game.board.BattleFieldDrawer");
				battleFieldDrawer = (BattleFieldDrawer) Class.forName(className).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			battleFieldDrawer = new BattleFieldDrawer();
		}
		return battleFieldDrawer;
	}

}
