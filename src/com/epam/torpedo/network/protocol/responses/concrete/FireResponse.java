package com.epam.torpedo.network.protocol.responses.concrete;

import com.epam.torpedo.Hunter;
import com.epam.torpedo.network.protocol.responses.Response;

public class FireResponse extends Response {

	private static final String COMMAND_NAME = "FIRE";
	private Hunter hunter;

	public FireResponse(Hunter hunter) {
		this.hunter = hunter;
	}

	@Override
	public String get() {
		return String.format("%s %s", COMMAND_NAME, hunter.nextShot().toString());
	}

}
