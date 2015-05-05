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
import com.epam.torpedo.game.GameConfig;
import com.epam.torpedo.resolvers.Resolver;
import com.epam.torpedo.targets.Shape;
import com.epam.torpedo.targets.ShipFactory;

public class FileBattleField extends BattleField {

	// TODO ez nem biztos hogy kellene ide, miért nem használom a ShipManagert? --> ld. BattleField::ShipManager
	private List<Shape> ships = new ArrayList<>();
	private List<Integer> numbersOfShips = new ArrayList<>();

	@Override
	public void createBattleField() {
		try {
			parse();
			shortByShipSize(ships);
			for (int i = 0; i < ships.size(); i++) {
				int counter = 0;
				int iterateCounter = 0;
				
				do {
					Ship ship = ShipFactory.getFreeShip(ships.get(i));
					try {
						addShip(ship);
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

	private void parse() throws IOException {
		String fileContent = load();
		StringTokenizer st = new StringTokenizer(fileContent, "\n");

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
