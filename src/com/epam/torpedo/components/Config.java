package com.epam.torpedo.components;

import java.io.File;

import com.epam.torpedo.Drawable;
import com.epam.torpedo.board.drawers.BattleFieldDrawer;
import com.epam.torpedo.resolvers.datasources.PropertyReader;

public class Config {

	private static PropertyReader properties = new PropertyReader();

	private static Drawable battleFieldDrawer;
	
	public static Dimension getBattleFieldDimension() {
		int width = Integer.parseInt(properties.getProperty("boardWidth"));
		int height = Integer.parseInt(properties.getProperty("boardHeight"));
		return new Dimension(width, height);
	}

	public static File getDataFile() {
		return new File(properties.getProperty("dataFile"));
	}

	public static Drawable getBattleFieldDrawer() {
		try {
			if(battleFieldDrawer == null) {
				String className = properties.getProperty("battleFieldDrawer");
				battleFieldDrawer = (Drawable) Class.forName(className).newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			battleFieldDrawer = new BattleFieldDrawer();
		}
		return battleFieldDrawer;
	}
	
}
