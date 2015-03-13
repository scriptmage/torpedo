package com.epam.torpedo.network.protocol.responses.concrete;

import com.epam.torpedo.network.protocol.responses.Response;

public class SunkResponse extends Response {

	private static final String COMMAND_NAME = "SUNK";

	@Override
	public String get() {
		return COMMAND_NAME;
	}

}
