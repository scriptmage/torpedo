package com.epam.torpedo.network.protocol.responses.concrete;

import com.epam.torpedo.network.protocol.responses.Response;

public class MissResponse extends Response {

	private static final String COMMAND_NAME = "MISS";

	@Override
	public String get() {
		return COMMAND_NAME;
	}

}
