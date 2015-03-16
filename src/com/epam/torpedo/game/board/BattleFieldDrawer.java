package com.epam.torpedo.game.board;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Drawable;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.CoordinateSet;

public class BattleFieldDrawer implements Drawable {

	private BattleField battleField;

	public BattleFieldDrawer(BattleField battleField) {
		this.battleField = battleField;
	}

	@Override
	public void draw(Hunter hunter) {
		StringBuilder sb = new StringBuilder();
		CoordinateSet shipsCoordinates = battleField.getAllShipCoords();
		CoordinateSet shots = hunter.getShots();

		for (int y = 0; y < battleField.getHeight(); y++) {
			for (int x = 0; x < battleField.getWidth(); x++) {
				char marker = '.';

				if (shipsCoordinates.contains(new Coordinate(x, y))) {
					marker = 'O';
					if (shots.contains(new Coordinate(x, y))) {
						marker = 'X';
					}
				}

				sb.append(marker + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
