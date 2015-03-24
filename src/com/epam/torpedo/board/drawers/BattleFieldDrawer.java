package com.epam.torpedo.board.drawers;

import com.epam.torpedo.Drawable;
import com.epam.torpedo.Hunter;
import com.epam.torpedo.components.Config;
import com.epam.torpedo.components.CoordinateSet;
import com.epam.torpedo.components.Dimension;

public class BattleFieldDrawer implements Drawable {

	@Override
	public void draw(CoordinateSet pointsOfShip, Hunter hunter) {
		StringBuilder sb = new StringBuilder();
		CoordinateSet shots = hunter.getShots();

		Dimension dimensionOfBattleField = Config.getBattleFieldDimension();
		for (int y = 0; y < dimensionOfBattleField.getHeight(); y++) {
			for (int x = 0; x < dimensionOfBattleField.getWidth(); x++) {
				char marker = '.';

				if (pointsOfShip.contains(x, y)) {
					marker = 'O';
					if (shots.contains(x, y)) {
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
