package com.epam.torpedo.network;

import java.util.StringTokenizer;

import com.epam.torpedo.ReadWriteSocket;
import com.epam.torpedo.components.Coordinate;

public class Protocol extends ReadWriteSocket {

	private Object params;
	private String command;

	public void hello(String param) {
		sendCommand("HELLO", param);
	}

	public void hit() {
		sendCommand("HIT");
	}

	public void miss() {
		sendCommand("HIT");
	}

	public void error(String message) {
		sendCommand("ERROR", message);
	}

	public void win() {
		sendCommand("YOU WON");
	}

	public void fire(Coordinate coordinate) {
		sendCommand("FIRE", coordinate.toString());
	}

	@Override
	public String readCommand() {
		String input = super.readCommand();
		StringTokenizer commandParser = new StringTokenizer(input, " ");
		command = commandParser.nextToken();
		createParams(command, commandParser);
		return command;
	}

	private void createParams(String command, StringTokenizer commandParser) {
		switch (command) {
		case "FIRE":
			int x = Integer.parseInt(commandParser.nextToken());
			int y = Integer.parseInt(commandParser.nextToken());
			params = new Coordinate(x, y);
			break;
		case "ERROR":
			params = commandParser.nextToken();
			break;
		}
	}
	
	public String getErrorMessage() {
		return (String) params;
	}
	
	public Coordinate getCoordinate() {
		return (Coordinate) params;
	}

}