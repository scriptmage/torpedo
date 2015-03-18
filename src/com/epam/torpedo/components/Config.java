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
	private static File dataFile;
	private static Dimension dimension;;

	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(CONFIG_FILE));
		} catch (IOException e) {
			System.out.println("Properties file not found: " + CONFIG_FILE);
		}
	}

	public static Dimension getBattleFieldDimension() {
		if (dimension == null) {
			int width = Integer.parseInt(properties.getProperty("boardWidth", "25"));
			int height = Integer.parseInt(properties.getProperty("boardHeight", "25"));
			dimension = new Dimension(width, height);
		}
		return dimension;
	}

	public static File getDataFile() {
		if (dataFile == null) {
			dataFile = new File(properties.getProperty("dataFile", "ships.dat"));
		}
		return dataFile;
	}

	public static BattleField getBattleField() {
		if (battleField == null) {
			battleField = new FileBattleField();
//			battleField.setDrawer(getBattleFieldDrawer());
		}

		return battleField;
	}

	public static Startable getGame() {
		if (game == null) {
			game = new SocketGame();
		}
		return game;
	}

	public static Hunter getHunter() {
		if (hunter == null) {
			hunter = new RandomHunter();
		}
		return hunter;
	}

	public static BattleFieldDrawer getBattleFieldDrawer() {
		if (battleFieldDrawer == null) {
			battleFieldDrawer = new BattleFieldDrawer();
		}
		return battleFieldDrawer;
	}

}
