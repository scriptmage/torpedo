package com.epam.torpedo.protocols;

import java.util.StringTokenizer;

import com.epam.torpedo.BattleField;
import com.epam.torpedo.Protocol;
import com.epam.torpedo.components.CoordinateSet;

public class FirstProtocol implements Protocol {
	
	private BattleField battlefield;

	public void setBattlefield(BattleField battlefield) {
		this.battlefield = battlefield;
	}

	@Override
	public String processInput(String input) {
		String answer = "ERROR Protocol";
		StringTokenizer command = new StringTokenizer(input.toUpperCase(), " ");

		switch (command.nextToken()) {
		case "":
			answer = String.format("HELLO %d %d", battlefield.getWidth(), battlefield.getHeight());
		case "FIRE":
			try {
				int x = Integer.parseInt(command.nextToken());
				int y = Integer.parseInt(command.nextToken());
				battlefield.validatePosition(x, y);
				
				CoordinateSet allShipCoords = battlefield.getAllShipCoords();
				if(allShipCoords.contains(x, y)) {
					answer = "HIT";
				} else {
					answer = "MISS";
				}
			} catch(NumberFormatException e) {
				answer = "ERROR Invalid FIRE params";
			} catch(IllegalArgumentException e) {
				answer = "ERROR Invalid FIRE position";
			}
			break;
		}

		System.out.println("Input: " + input);
		System.out.println("Answer: " + answer);
		return answer;
	}

}
