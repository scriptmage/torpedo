package com.epam.torpedo.game;

import java.io.File;
import java.io.IOException;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.board.battlefields.FileBattleField;
import com.epam.torpedo.game.hunters.RandomHunter;

public class Application {

	private static final String SHIPS_DATA_FILE = "ships.dat";
	private static final int BATTLEFIELD_WIDTH = 20;
	private static final int BATTLEFIELD_HEIGHT = 20;

	public static void main(String[] args) {
		try {
			Game game = new Game();
			Dimension dimensionOfBattleField = new Dimension(BATTLEFIELD_WIDTH,
					BATTLEFIELD_HEIGHT);
			FileBattleField battleField = new FileBattleField(
					dimensionOfBattleField);
			Hunter hunter = new RandomHunter();
			int numberOfShips = battleField.parser(new File(SHIPS_DATA_FILE));

			game.setBattleFieldFillingStrategy(battleField);
			game.setHunterStrategy(hunter);
			game.start(numberOfShips);
		} catch (RuntimeException | IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Game Over");
	}

}
