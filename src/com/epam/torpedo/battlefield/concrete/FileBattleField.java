package com.epam.torpedo.battlefield.concrete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.epam.torpedo.Ship;
import com.epam.torpedo.battlefield.BattleField;
import com.epam.torpedo.components.Coordinate;
import com.epam.torpedo.components.Dimension;
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.resolvers.Resolver;
import com.epam.torpedo.targets.Shape;
import com.epam.torpedo.targets.ShipFactory;

public class FileBattleField extends BattleField {

	private List<Integer> numbersOfShips = new ArrayList<>();

	@Override
	public void createBattleField() {
		// TODO a quartert és a működést kiemelni egy osztályba
		int counterOfBlock = 1;
		try {
			List<Shape> ships = parse();
			shortByShipSize(ships);
			Dimension dimension = getDimension();
			for (int i = 0; i < ships.size(); i++) {
				int counter = 0;
				int iterateCounter = 0;
				
				do {
					Coordinate position;
					switch (counterOfBlock) {
					case 1:
						position = Coordinate.getRandomIntervalCoordinate(0, dimension.getWidth() / 4, 0, dimension.getHeight() / 4);
						break;
					case 2:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 4, dimension.getWidth() / 2, 0, dimension.getHeight() / 4);
						break;
					case 3:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2, dimension.getWidth() / 2 + dimension.getWidth() / 4, 0, dimension.getHeight() / 4);
						break;
					case 4:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2 + dimension.getWidth() / 4, dimension.getWidth(), 0, dimension.getHeight() / 4);
						break;
					case 5:
						position = Coordinate.getRandomIntervalCoordinate(0, dimension.getWidth() / 4, dimension.getHeight() / 4, dimension.getHeight() / 2);
						break;
					case 6:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 4, dimension.getWidth() / 2, dimension.getHeight() / 4, dimension.getHeight() / 2);
						break;
					case 7:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2, dimension.getWidth() / 2 + dimension.getWidth() / 4, dimension.getHeight() / 4, dimension.getHeight() / 2);
						break;
					case 8:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2 + dimension.getWidth() / 4, dimension.getWidth(), dimension.getHeight() / 4, dimension.getHeight() / 2);
						break;
					case 9:
						position = Coordinate.getRandomIntervalCoordinate(0, dimension.getWidth() / 4, dimension.getHeight() / 2, dimension.getHeight() / 4 + dimension.getHeight() / 2);
						break;
					case 10:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 4, dimension.getWidth() / 2, dimension.getHeight() / 2, dimension.getHeight() / 4 + dimension.getHeight() / 2);
						break;
					case 11:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2, dimension.getWidth() / 2 + dimension.getWidth() / 4, dimension.getHeight() / 2, dimension.getHeight() / 4 + dimension.getHeight() / 2);
						break;
					case 12:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2 + dimension.getWidth() / 4, dimension.getWidth(), dimension.getHeight() / 2, dimension.getHeight() / 4 + dimension.getHeight() / 2);
						break;
					case 13:
						position = Coordinate.getRandomIntervalCoordinate(0, dimension.getWidth() / 4, dimension.getHeight() / 4 + dimension.getHeight() / 2, dimension.getHeight());
						break;
					case 14:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 4, dimension.getWidth() / 2, dimension.getHeight() / 4 + dimension.getHeight() / 2, dimension.getHeight());
						break;
					case 15:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2, dimension.getWidth() / 2 + dimension.getWidth() / 4, dimension.getHeight() / 4 + dimension.getHeight() / 2, dimension.getHeight());
						break;
					default:
						position = Coordinate.getRandomIntervalCoordinate(dimension.getWidth() / 2 + dimension.getWidth() / 4, dimension.getWidth(), dimension.getHeight() / 4 + dimension.getHeight() / 2, dimension.getHeight());
						break;
					}
					
					Ship ship = ShipFactory.getFreeShip(ships.get(i));
					ship.setPosition(position);
					try {
						addShip(ship);
						counterOfBlock++;
						if (counterOfBlock > 16) {
							counterOfBlock = 1;
						}
						System.out.println(String.format("New ship is here: %d %d", ship.getPositionX(), ship.getPositionY()));
						iterateCounter = 0;
						counter++;
					} catch (RuntimeException e) {
						System.out.println(e.getMessage());
					}
					iterateCounter++;
				} while (counter < numbersOfShips.get(i) && iterateCounter < GameConfig.ITERATION_TOLERANCE);
				
				checkTolerance(iterateCounter);
			}
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	private List<Shape> parse() throws IOException {
		String fileContent = load();
		StringTokenizer st = new StringTokenizer(fileContent, "\n");
		List<Shape> ships = new ArrayList<>();

		int dimensionY = 0;
		int shipCounter = 0;

		Shape pointsOfShape = new Shape();
		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			StringTokenizer valueOfFields = new StringTokenizer(line, " ");

			int dimensionX = 0;
			while (valueOfFields.hasMoreTokens()) {
				String field = valueOfFields.nextToken();
				if (field.equals("x")) {
					pointsOfShape.add(dimensionX, dimensionY);
				} else if (field.matches("^\\d+$")) {
					int amountOfShip = Integer.parseInt(field);

					ships.add(pointsOfShape);
					pointsOfShape = new Shape();
					
					numbersOfShips.add(amountOfShip);
					shipCounter += amountOfShip;
					dimensionY = -1;
				}
				dimensionX++;
			}
			dimensionY++;
		}
		
		setMaxNumberOfShips(shipCounter);
		return ships;
	}
	
	private String load() throws IOException {
		StringBuilder fileContent = new StringBuilder();
		Resolver resolver = GameConfig.getResolver();
		File dataOfShips = new File(resolver.get("dataFile"));
		try (BufferedReader br = new BufferedReader(new FileReader(dataOfShips))) {
			String buffer = null;
			while ((buffer = br.readLine()) != null) {
				fileContent.append(buffer + "\n");
			}
			br.close();
		}
		return fileContent.toString();
	}

}
